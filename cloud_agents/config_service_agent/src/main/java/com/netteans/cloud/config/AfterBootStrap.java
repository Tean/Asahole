package com.netteans.cloud.config;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

@Component
public class AfterBootStrap implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(AfterBootStrap.class);

    @Autowired
    private ConsulClient consulClient;

    @Autowired
    private ApplicationContext context;

    public void registerThings(boolean force) {
        StringBuilder stringBuilder = new StringBuilder();
        File f = new File(this.getClass().getResource("/reg").getFile());
        File[] fs = f.listFiles();
        RegData regData = new RegData();

        for (File fin : fs) {
            if (fin.isDirectory()) {
                String pfxName = fin.getName();
                Prefix prefix = regData.get(pfxName);
                if (prefix == null) {
                    prefix = new Prefix();
                    regData.put(pfxName, prefix);
                }

                File[] profiles = fin.listFiles();
                for (File profile : profiles) {
                    String fname = profile.getName();
                    logger.info("{}>{}", pfxName, fname);

                    InputStreamReader is = null;
                    try {
                        is = new InputStreamReader(new FileInputStream(Paths.get(f.getPath(), pfxName, fname).toFile()));
                        Scanner sc = new Scanner(is);
                        while (sc.hasNextLine()) {
                            stringBuilder.append(sc.nextLine()).append(System.getProperty("line.separator"));
                        }
                    } catch (IOException e) {
                        logger.error("{}", e);
                    } finally {
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e) {
                                logger.error("{}", e);
                            }
                        }
                    }

                    String key = fname.substring(0, fname.lastIndexOf('.'));
                    Context context = null;
                    if (fname.indexOf('-') > 0) {
                        String contextName = fname.substring(0, fname.indexOf('-'));
                        key = fname.substring(fname.indexOf('-') + 1, fname.lastIndexOf('.'));

                        context = prefix.get(contextName);
                        if (context == null) {
                            context = new Context();
                            prefix.put(contextName, context);
                        }
                        context.put(key, stringBuilder.toString());
                    } else {
                        prefix.putValue(key, stringBuilder.toString());
                    }

                    stringBuilder.delete(0, stringBuilder.length());
                }
            }
        }
        logger.info("load reg data: {}", stringBuilder.toString());

        regData.keys().forEach(pfxkey -> {
            Prefix prefix = regData.get(pfxkey);
            prefix.keys().forEach(contextName -> {
                Context context = prefix.get(contextName);
                context.keys().forEach(key -> {
                    String kvKey = pfxkey + "/" + contextName + "/" + key;

                    Response<GetValue> val = consulClient.getKVValue(kvKey);
                    if (val.getValue() == null || force) {
                        consulClient.setKVValue(kvKey, context.get(key));
                        logger.info("new backend data: {}", kvKey);
                    } else {
                        String value = val.getValue().getDecodedValue();
                        logger.info("exists backend data: {}", kvKey);
                    }
                });
            });
            prefix.valueKeys().forEach(valKey -> {
                String kvKey = pfxkey + "/" + valKey;
                String value = prefix.getValue(valKey);
                Response<GetValue> val = consulClient.getKVValue(kvKey);
                if (val.getValue() == null || force) {
                    consulClient.setKVValue(kvKey, value);
                    logger.info("new backend data: {}", kvKey);
                } else {
                    String v = val.getValue().getDecodedValue();
                    logger.info("exists backend data: {}", kvKey);
                }
            });
        });
    }

    @Override
    public void run(String... args) {
        String[] profiles = context.getEnvironment().getActiveProfiles();
        if (profiles.length == 0) {
            profiles = context.getEnvironment().getDefaultProfiles();
        }
        logger.info("active profile is {}", profiles);
        Map<String, String> param = new TreeMap<>();
        for (String arg :
                args) {
            String[] kv = arg.split("=");
            if (kv.length > 0) {
                String key = kv[0];
                logger.info(String.format("load params: %s -> %s", kv[0], kv.length > 1 ? kv[1] : "none"));
                param.put(kv[0], kv.length > 1 ? kv[1] : null);
            }
        }
        logger.info("started & load server.port:" + 000);
        registerThings(param.get("--force").equalsIgnoreCase("true"));
    }
}
