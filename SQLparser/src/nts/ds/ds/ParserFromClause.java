package nts.ds.ds;

import java.util.ArrayList;
import java.util.List;

public class ParserFromClause
{
    public ParserFromClause AddJoin(ParserJoinOperator join)
    {
        this.tables.add(join);
        return this;
    }

    public ParserFromClause AddTable(ParserObjectName tableName, String asName, 
        ParserFromIndexed indexed, ParserExpression onExpr, List<String> usingOpt)
    {
    	this.tables.add(new ParserFromTable(tableName, asName, indexed, onExpr, usingOpt));
        return this;
    }
    
    public ParserFromClause AddTable(ParserFromTable fromTable)
    {
    	this.tables.add(fromTable);
    	return this;
    }
    
    public ParserFromClause AddExpr(ParserExpression fromTable)
    {
    	this.tables.add(fromTable);
    	return this;
    }
    
    public ParserFromClause AddObject(Object obj)
    {
    	if (obj instanceof ParserFromTable) {
    		this.AddTable((ParserFromTable) obj);
    	} else if (obj instanceof ParserJoinOperator) {
    		this.AddJoin((ParserJoinOperator) obj);
    	}
    	return this;
    }

    public ParserFromClause AddInternalTable(ParserFromInternalTable itable, String asName,
        ParserExpression onExpr, List<String> usingOpt)
    {
    	this.tables.add(new ParserFromTable(itable, asName, onExpr, usingOpt));
        return this;
    }

    public List<Object> getFromTables()
    {
        return this.tables;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserFromClause dst = (obj instanceof ParserFromClause) ? (ParserFromClause) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.CompareList(this.tables, dst.tables))
            return false;

        return true;
    }

    @Override
    public String toString()
    {
        if (tables == null || tables.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tables.size(); i++)
        {
            if (tables.get(i) instanceof ParserExpression)
                sb.append(tables.get(i).toString());
            /*
            else
                sb.append(Utils.GetJoinOperatorString((SQLiteJoinOperator)tables.get(i)));
            */
            if (i < tables.size() - 1)
                sb.append(" ");
        } // for
        return sb.toString();
    }

    @Override
    public Object clone()
    {
        List<Object> tlist = null;
        if (this.tables != null)
        {
            tlist = new ArrayList<Object>();
            for (Object obj : this.tables)
            {
                if (obj instanceof ParserExpression)
                {
                	ParserExpression ft = (ParserExpression)obj;
                    tlist.add(ft.clone());
                }
                /*
                else if (obj instanceof SQLiteJoinOperator)
                    tlist.add(obj);
                */
            } // foreach
        }

        ParserFromClause res = new ParserFromClause();
        res.tables = tlist;
        return res;
    }

    private List<Object> tables = new ArrayList<Object>();
}
