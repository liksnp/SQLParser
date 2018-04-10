package nts.ds.ds;

public class ParserFromIndexed
{
    public ParserFromIndexed()
    {
    }

    public ParserFromIndexed(String by)
    {
        setIndexedBy(by);
    }

    public boolean getIsIndexed()
    {
        return this.by != null;
    }

    public String getIndexedBy()
    {
        return this.by;
    }
    
    public void setIndexedBy(String by)
    {
    	this.by = Utils.QuoteIfNeeded(by);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserFromIndexed dst = (obj instanceof ParserFromIndexed) ? (ParserFromIndexed) obj : null;
        if (dst == null)
            return false;

        return this.by == dst.by;
    }

    @Override
    public String toString()
    {
        if (getIsIndexed())
            return "INDEXED BY " + this.by;
        else
            return "NOT INDEXED";
    }

    @Override
    public Object clone()
    {
        ParserFromIndexed res = new ParserFromIndexed();
        res.by = this.by;
        return res;
    }

    private String by;
}
