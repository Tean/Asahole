package com.netteans.agent.test;

import org.junit.Test;

import java.lang.reflect.Type;

public class OtherTestBootStrap {
    @Test
    public void test() {
        SimpleQuery<ISelect> simpleQuery = new SimpleQuery<ISelect>() {
            @Override
            public ISelect from() {
                return null;
            }

            @Override
            public ISelect where() {
                return null;
            }
        };
    }
}


interface IQuery<I> {
    I from();
    I where();
    I jonn(IQuery<I> query);
}

abstract class SimpleQuery<I> implements IQuery<I> {
    @Override
    public I jonn(IQuery<I> query) {
        return null;
    }
}

interface ISelect extends IQuery<ISelect>{

}
interface IUpdate extends IQuery<IUpdate>{}
interface IDelete extends IQuery<IDelete>{}
interface ICreate extends IQuery<ICreate>{}






