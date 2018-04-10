package nts.ds.ds;

public class ParserIdExpression extends ParserExpression
{
    public ParserIdExpression(String id)
    {
    	this.id = id;
    }

    public String getId()
    {
        return this.id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public boolean IsConstant(boolean allowNull)
    {
        return false;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserIdExpression dst = (obj instanceof ParserIdExpression) ? (ParserIdExpression) obj : null;
        if (dst == null)
            return false;

        if (this.id != dst.id)
            return false;

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        return this.id;
    }

    @Override
    public Object clone()
    {
        ParserExpression res = new ParserIdExpression(this.id);
        return res;
    }

    private String id;
}
