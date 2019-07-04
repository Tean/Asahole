package com.netteans.agent.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class OtherTestBootStrap {
    private static final Logger logger = LoggerFactory.getLogger(OtherTestBootStrap.class);

    @Test
    public void test() {
        Object k = "1";
        Object v = "2h";
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(k, k);
        concurrentHashMap.put(v, k);
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
                                new Or(new RLike(() -> "what", "ask")).and(new And(new LLike(() -> "what", "qqq")))
                        ).or(new And(new Equal(() -> "what", "say")))
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
    String LIKEQUOTE = "%";

    String label();

    I from(ITable table);
}

abstract class AbstractCalc {
    final String LABEL;
    final AbstractCondition condition;
    String nLabel = "";
    AbstractCalc preCalc;
    AbstractCalc nextCalc;

    public AbstractCalc(String label, AbstractCondition condition) {
        this.LABEL = label;
        this.condition = condition;
    }

    AbstractCalc and(AbstractCalc andCalc) {
        andCalc.preCalc = this;
        this.nextCalc = andCalc;
        this.nLabel = "And";
        return this;
    }

    AbstractCalc or(AbstractCalc orCalc) {
        orCalc.preCalc = this;
        this.nextCalc = orCalc;
        this.nLabel = "Or";
        return this;
    }

    abstract String toSql();
}

class And extends AbstractCalc {

    public And(AbstractCondition condition) {
        super("And", condition);
    }

    @Override
    public String toSql() {
        StringBuilder sql = new StringBuilder();
        if (this.condition != null) {
            sql.append(IQuery.SPACE).append(this.condition.toSql()).append(IQuery.SPACE);
            return sql.toString() + (this.nextCalc == null ? "" : IQuery.SPACE + this.nLabel + IQuery.SPACE + this.nextCalc.toSql());
        }
        return sql.toString();
    }
}

class Or extends AbstractCalc {
    public Or(AbstractCondition condition) {
        super("Or", condition);
    }

    @Override
    public String toSql() {
        StringBuilder sql = new StringBuilder();
        if (this.condition != null) {
            sql.append(IQuery.SPACE).append(this.condition.toSql()).append(IQuery.SPACE);
            return sql.toString() + (this.nextCalc == null ? "" : IQuery.SPACE + this.nLabel + IQuery.SPACE + this.nextCalc.toSql());
        }
        return sql.toString();
    }
}

interface IWhere<C extends AbstractCalc> {
    String LABEL = "where";

    List<C> calc();

    String toSql();

    IWhere<C> and(AbstractCalc and);

    IWhere<C> or(AbstractCalc or);
}

class Where<C> implements IWhere {

    final List<AbstractCalc> calc = new ArrayList<>();
    final AbstractCondition condition;
    private String nLabel;

    public Where(AbstractCondition condition) {
        this.condition = condition;
    }

    @Override
    public List<AbstractCalc> calc() {
        final List<AbstractCalc> ac = Arrays.asList(calc.toArray(new AbstractCalc[0]));
        return ac;
    }

    @Override
    public String toSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.condition.toSql() + (this.nLabel == null ? "" : IQuery.SPACE + this.nLabel) + IQuery.SPACE);
        for (AbstractCalc ac : calc) {
            stringBuilder.append(ac.toSql());
            stringBuilder.append(IQuery.SPACE);
        }
        return stringBuilder.toString();
    }

    @Override
    public Where<C> and(AbstractCalc and) {
        this.calc.add(and);
        this.nLabel = "And";
        return this;
    }

    @Override
    public IWhere or(AbstractCalc or) {
        this.calc.add(or);
        this.nLabel = "Or";
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

class Like<Type extends String> extends AbstractCondition<Type> {

    public Like(IColumn column, Type value) {
        super(column, value, "like");
    }

    @Override
    String toSql() {
        return this.column.getName() + IQuery.SPACE + this.operator + IQuery.SPACE + IQuery.SingleQuote + IQuery.LIKEQUOTE + this.value + IQuery.LIKEQUOTE + IQuery.SingleQuote;
    }
}

class LLike<Type extends String> extends Like<Type> {

    public LLike(IColumn column, Type value) {
        super(column, value);
    }

    @Override
    String toSql() {
        return this.column.getName() + IQuery.SPACE + this.operator + IQuery.SPACE + IQuery.SingleQuote + IQuery.LIKEQUOTE + this.value + IQuery.SingleQuote;
    }
}

class RLike<Type extends String> extends Like<Type> {

    public RLike(IColumn column, Type value) {
        super(column, value);
    }

    @Override
    String toSql() {
        return this.column.getName() + IQuery.SPACE + this.operator + IQuery.SPACE + IQuery.SingleQuote + this.value + IQuery.LIKEQUOTE + IQuery.SingleQuote;
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






