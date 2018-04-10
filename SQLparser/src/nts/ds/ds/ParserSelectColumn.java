package nts.ds.ds;

public class ParserSelectColumn
{
    public ParserSelectColumn(ParserExpression expr, String name)
    {
        this.expr = expr;
        this.setAsName(name);
    }

    public ParserSelectColumn(ParserObjectName tableName)
    {
    	this.tableName = tableName;
    }

    public ParserSelectColumn()
    {
    }

    public ParserExpression getExpression()
    {
        return this.expr;
    }
    
    public void setExpression(ParserExpression expr)
    {
    	this.expr = expr;
    }

    public String getAsName()
    {
        return this.asName;
    }
    
    public void setAsName(String asName)
    {
    	this.asName = Utils.QuoteIfNeeded(asName);
    }

    public ParserObjectName getTableName()
    {
        return this.tableName;
    }
    
    public void setTableName(ParserObjectName tableName)
    {
    	this.tableName = tableName;
    }

    public boolean getIsSingleWildcard()
    {
        return this.expr == null && this.tableName == null && this.asName == null;
    }

    @Override 
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserSelectColumn dst = (obj instanceof ParserSelectColumn) ? (ParserSelectColumn) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.CompareMany(this.expr, dst.expr, this.tableName, dst.tableName))
            return false;
        if (this.asName != dst.asName)
            return false;

        return true;
    }

    @Override
    public String toString()
    {
        if (tableName != null)
            return tableName.toString() + ".*";
        if (expr != null && asName != null)
            return expr.toString() + " AS " + asName;
        else if (expr != null)
            return expr.toString();
        return "*";
    }

    @Override
    public Object clone()
    {
        ParserExpression expr = null;
        if (this.expr != null)
            expr = (ParserExpression)this.expr.clone();
        ParserObjectName tableName = null;
        if (this.tableName != null)
            tableName = (ParserObjectName)this.tableName.clone();

        ParserSelectColumn res = new ParserSelectColumn();
        res.expr = expr;
        res.asName = this.asName;
        res.tableName = tableName;
        return res;
    }

    private ParserExpression expr;
    private String asName;
    private ParserObjectName tableName;
}
