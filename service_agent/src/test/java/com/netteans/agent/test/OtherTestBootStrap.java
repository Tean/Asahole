package com.netteans.agent.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OtherTestBootStrap {
    private final static Logger logger = LoggerFactory.getLogger(OtherTestBootStrap.class);

    @Test
    public void test() {
        Select select = new Select() {

            @Override
            public Select from(ITable table) {
                this.from = new AbstractFrom(table);
                this.from.table(table);
                return this;
            }

            @Override
            public String toSql() {
                StringBuilder sql = new StringBuilder();
                sql.append(this.label());
                sql.append(SPACE).append(this.columns.toSql());
                sql.append(SPACE).append(this.from.LABEL);
                sql.append(SPACE).append(this.from.table().name());
                sql.append(SPACE).append(this.where.LABEL);
                if (this.where != null) {
                    sql.append(SPACE).append(this.where.toSql());
                }
                return sql.toString();
            }
        };
        logger.info("{}", select.query(new IColumn[]{() -> "what", () -> "the", () -> "fuck"}).from(new ITable() {
            @Override
            public String name() {
                return "Test";
            }

            @Override
            public IColumn[] columns() {
                return new IColumn[0];
            }

            @Override
            public IRow[] rows() {
                return new IRow[0];
            }
        }).where(
                new Where(
                        new Equal<String>(() -> "what", "xxx"))
                        .and(
                                new Or(
                                        new AbstractCalc[]{
                                                new And(new GT<>(() -> "the", 212)),
                                                new And(new LT<Double>(() -> "fuck", 3.14159))
                                        }
                                )
                        )
        ).toSql());
    }
}

interface IFrom {
    String LABEL = "From ";

    IFrom table(ITable table);

    ITable table();
}

class AbstractFrom implements IFrom {

    ITable table;

    AbstractFrom(ITable table) {
        this.table = table;
    }

    @Override
    public IFrom table(ITable table) {
        this.table = table;
        return this;
    }

    @Override
    public ITable table() {
        return this.table;
    }
}

interface ITable {
    String name();

    IColumn[] columns();

    IRow[] rows();
}


interface IColumns {
    IColumn[] getColumns();

    String toSql();
}

interface IColumn {
    String getName();
}

class Columns implements IColumns {
    IColumn[] columns;

    public Columns(IColumn... columns) {
        this.columns = columns;
    }

    @Override
    public String toSql() {
        StringBuilder col = new StringBuilder();
        if (columns.length == 0) return "* ";
        for (int i = 0; i < columns.length; i++) {
            col.append(columns[i].getName());
            if (i < columns.length - 1) {
                col.append(",");
            }
        }
        return col.toString();
    }

    @Override
    public IColumn[] getColumns() {
        return this.columns;
    }
}

interface IRow {
    long getRowIndex();

    Class<?> getValue(int columnIndex);

    Class<?> getValue(IColumn column);
}

interface IQuery<I extends AbstractQuery> {
    String SPACE = " ";
    String SingleQuote = "'";

    String label();

    I from(ITable table);
}

abstract class AbstractCalc {
    final String LABEL;
    final AbstractCondition condition;
    final AbstractCalc[] calcs;

    public AbstractCalc(String label, AbstractCondition condition, AbstractCalc[] calcs) {
        this.LABEL = label;
        this.condition = condition;
        this.calcs = calcs;
    }

    abstract String toSql();
}

class And extends AbstractCalc {

    public And(AbstractCondition condition) {
        super("And", condition, new AbstractCalc[0]);
    }

    public And(AbstractCalc[] calcs) {
        super("And", null, calcs);
    }

    @Override
    public String toSql() {
        StringBuilder sql = new StringBuilder();
        if (this.condition != null) {
            sql.append(IQuery.SPACE).append(this.condition.toSql()).append(IQuery.SPACE);
            return sql.toString();
        }
        sql.append("And").append(IQuery.SPACE);
        if (this.calcs.length > 0) {
            sql.append("(");
            for (int i = 0; i < this.calcs.length; i++) {
                sql.append(this.calcs[i].toSql());
            }
            sql.append(")");
        }
        return sql.toString();
    }
}

class Or extends AbstractCalc {
    public Or(AbstractCondition condition) {
        super("Or", condition, new AbstractCalc[0]);
    }

    public Or(AbstractCalc[] calcs) {
        super("Or", null, calcs);
    }

    @Override
    public String toSql() {
        StringBuilder sql = new StringBuilder();
        if (this.condition != null) {
            sql.append(IQuery.SPACE).append(this.condition.toSql()).append(IQuery.SPACE);
            return sql.toString();
        }
        sql.append("Or").append(IQuery.SPACE);
        if (this.calcs.length > 0) {
            sql.append("(");
            for (int i = 0; i < this.calcs.length; i++) {
                sql.append(this.calcs[i].toSql());
                if (i < this.calcs.length - 1)
                    sql.append(this.calcs[i].LABEL);
            }
            sql.append(")");
        }
        return sql.toString();
    }
}

interface IWhere<C extends AbstractCalc> {
    String LABEL = "where";

    C calc();

    String toSql();

    IWhere<C> and(AbstractCalc or);
}

class Where<C> implements IWhere {

    AbstractCalc calc;
    final AbstractCondition condition;

    public Where(AbstractCondition condition) {
        this.condition = condition;
    }

    @Override
    public AbstractCalc calc() {
        return this.calc;
    }

    @Override
    public String toSql() {
        return this.condition.toSql() + IQuery.SPACE + calc.toSql();
    }

    @Override
    public Where<C> and(AbstractCalc calc) {
        this.calc = calc;
        return this;
    }
}

abstract class AbstractCondition<Type> {
    final IColumn column;
    final Type value;
    final String operator;

    public AbstractCondition(IColumn column, Type value, String operator) {
        this.column = column;
        this.value = value;
        this.operator = operator;
    }

    public IColumn column() {
        return this.column;
    }

    public Type value() {
        return this.value;
    }

    String toSql() {
        if (value.getClass().equals(String.class))
            return this.column.getName() + IQuery.SPACE + this.operator + IQuery.SPACE + IQuery.SingleQuote + this.value + IQuery.SingleQuote;
        if (value instanceof Number || value.getClass().equals(Byte.class) || value.getClass().equals(Boolean.class))
            return this.column.getName() + IQuery.SPACE + this.operator + IQuery.SPACE + this.value;
        return value.getClass().getName();
    }
}

class Equal<Type> extends AbstractCondition<Type> {

    public Equal(IColumn column, Type value) {
        super(column, value, "=");
    }
}

class GT<Type extends Number> extends AbstractCondition<Type> {

    public GT(IColumn column, Type value) {
        super(column, value, ">");
    }
}

class GTE<Type extends Number> extends AbstractCondition<Type> {

    public GTE(IColumn column, Type value) {
        super(column, value, ">=");
    }
}

class LT<Type extends Number> extends AbstractCondition<Type> {

    public LT(IColumn column, Type value) {
        super(column, value, "<");
    }
}

class LTE<Type extends Number> extends AbstractCondition<Type> {

    public LTE(IColumn column, Type value) {
        super(column, value, "<=");
    }
}

interface IJonn<Query> {
    Query query();
}

abstract class AbstractQuery implements IQuery {
    IFrom from;
    IColumns columns;
    IWhere where;

    abstract AbstractQuery query(IColumn... columns);

    abstract String toSql();

    abstract AbstractQuery where(IWhere where);
}

abstract class Select extends AbstractQuery {

    @Override
    public String label() {
        return "Select";
    }

    @Override
    Select query(IColumn... columns) {
        this.columns = new Columns(columns);
        return this;
    }

    @Override
    Select where(IWhere where) {
        this.where = where;
        return this;
    }
}

abstract class Update extends AbstractQuery {
    @Override
    public String label() {
        return "Update";
    }

    @Override
    Update query(IColumn... columns) {
        this.columns = new Columns(columns);
        return this;
    }
}

abstract class Delete extends AbstractQuery {
    @Override
    public String label() {
        return "Delete";
    }

    @Override
    Delete query(IColumn... columns) {
        this.columns = new Columns(columns);
        return this;
    }
}

abstract class Create extends AbstractQuery {
    @Override
    public String label() {
        return "Create";
    }

    @Override
    Create query(IColumn... columns) {
        this.columns = new Columns(columns);
        return this;
    }
}






