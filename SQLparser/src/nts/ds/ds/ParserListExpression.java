package nts.ds.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ParserListExpression extends ParserExpression implements Iterable<ParserExpression> {
    public final List<ParserExpression> list = new ArrayList<ParserExpression>();

    public ParserListExpression() {
    }
    public ParserListExpression(ParserExpression... exprs) {
        Collections.addAll(list, exprs);
    }

    public void add(ParserExpression expr) {
        list.add(expr);
    }

    @Override
    public Iterator<ParserExpression> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("(");
        for (ParserExpression expression : list) {
            s.append(expression);
            s.append(", ");
        }
        if (!list.isEmpty()) {
            s.setLength(s.length() - ", ".length());
        }
        s.append(")");
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParserListExpression that = (ParserListExpression) o;

        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }
}
