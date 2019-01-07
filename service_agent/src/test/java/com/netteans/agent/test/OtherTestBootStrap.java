package com.netteans.agent.test;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

public class OtherTestBootStrap {
    @Test
    public void test() {
        SimpleQuery<ISelect> simpleQuery = new SimpleQuery<ISelect>() {
            @Override
            public ISelect from() {
                return null;
            }

            public ISelect where() {
                return null;
            }
        };
    }
}


interface IQuery<I> {
    I from();
}

interface ICalc {

}

interface IAnd extends ICalc {
    ICalc And();
}

interface IOr extends ICalc {
    ICalc And();
}

interface IWhere<Condition> {
    Condition condition();
}

interface ICondition {
    List<ICalc> conditions();
}

interface IJonn<Query> {
    Query query();
}

abstract class SimpleQuery<I> implements IQuery<I> {
    public I jonn(IQuery<I> query) {
        return null;
    }
}

interface ISelect extends IQuery<ISelect> {

}

interface IUpdate extends IQuery<IUpdate> {
}

interface IDelete extends IQuery<IDelete> {
}

interface ICreate extends IQuery<ICreate> {
}






