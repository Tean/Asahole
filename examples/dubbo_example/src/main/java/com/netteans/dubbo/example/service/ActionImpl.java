package com.netteans.dubbo.example.service;

import com.netteans.dubbo.example.domain.ActionDetail;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service(version = "${demo.service.version}")
public class ActionImpl implements IAction<ActionDetail> {
    private final UUID uid = UUID.randomUUID();
    private final List<ActionDetail> adlist = new ArrayList();

    public ActionImpl() {
        for (int i = 0; i < 3; i++) {
            ActionDetail ad = new ActionDetail();
            ad.setId(i + 1);
            ad.setName("adDetail" + i);
            adlist.add(ad);
        }
    }

    @Override
    public ActionDetail getOne(String actparam) {
        AtomicReference<ActionDetail> rad = new AtomicReference<>();
        adlist.forEach(ad -> {
            if (ad.getName().startsWith(actparam))
                rad.set(ad);
        });
        return rad.get();
    }

    @Override
    public List<ActionDetail> getList() {
        return adlist;
    }

    @Override
    public String getInstance() {
        return uid.toString();
    }
}
