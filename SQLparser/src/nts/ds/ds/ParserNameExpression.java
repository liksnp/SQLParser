package nts.ds.ds;

public class ParserNameExpression extends ParserExpression
{
    private ParserNameExpression()
    {
    }

    public ParserNameExpression(ParserObjectName name)
    {
    	this.name = name;
    }

    public ParserObjectName getName()
    {
        return this.name;
    }
    
    public void Name(ParserObjectName name)
    {
        this.name = name;
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

        ParserNameExpression dst = (obj instanceof ParserNameExpression) ? (ParserNameExpression) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.Compare(this.name, dst.name))
            return false;

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        return name.toString();
    }

    @Override
    public Object clone()
    {            
        ParserObjectName name = null;
        if (this.name != null)
            name = (ParserObjectName)this.name.clone();

        ParserNameExpression res = new ParserNameExpression();
        res.name = name;
        return res;
    }

    private ParserObjectName name;
}
