package nts.ds.ds;

public class ParserFromInternalTable
{
    private ParserFromInternalTable()
    {
    }

    public ParserFromInternalTable(ParserSelectStatement select)
    {
        this.select = select;
    }

    public ParserFromInternalTable(ParserFromClause from)
    {
        this.from = from;
    }

    public ParserSelectStatement getSelectStatement()
    {
        return this.select;
    }
    
    public void setSelectStatement(ParserSelectStatement select)
    {
        this.select = select;
    }

    public ParserFromClause getFromClause()
    {
        return this.from;
    }
    
    public void setFromClause(ParserFromClause from)
    {
        this.from = from;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserFromInternalTable dst = (obj instanceof ParserFromInternalTable) ? (ParserFromInternalTable) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.CompareMany(this.select, dst.select, this.from, dst.from))
            return false;

        return true;
    }

    @Override
    public String toString()
    {
        if (this.select != null)
            return this.select.toString();
        else
            return this.from.toString();
    }

    @Override
    public Object clone()
    {
        ParserSelectStatement stmt = null;
        if (this.select != null)
            stmt = (ParserSelectStatement)this.select.clone();
        ParserFromClause f = null;
        if (this.from != null)
            f = (ParserFromClause)this.from.clone();
        ParserFromInternalTable res = new ParserFromInternalTable();
        res.from = f;
        res.select = stmt;
        return res;
    }

    private ParserSelectStatement select;
    private ParserFromClause from;
}
