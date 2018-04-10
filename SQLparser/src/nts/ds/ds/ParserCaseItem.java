package nts.ds.ds;

public class ParserCaseItem
{
    private ParserCaseItem()
    {
    }

    public ParserCaseItem(ParserExpression whenExpr, ParserExpression thenExpr)
    {
    	this.when = whenExpr;
    	this.then = thenExpr;
    }

    public ParserExpression getWhenExpression()
    {
        return this.when;
    }
    
    public void setWhenExpression(ParserExpression when)
    {
        this.when = when;
    }

    public ParserExpression getThenExpression()
    {
        return this.then;
    }
    
    public void setThenExpression(ParserExpression then)
    {
        this.then = then;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserCaseItem dst = (obj instanceof ParserCaseItem) ? (ParserCaseItem) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.CompareMany(this.when, dst.when, this.then, dst.then))
            return false;

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        return "WHEN " + when.toString() + " THEN " + then.toString();
    }

    @Override
    public Object clone()
    {
        ParserExpression when = null;
        if (this.when != null)
            when = (ParserExpression)this.when.clone();
        ParserExpression then = null;
        if (this.then != null)
            then = (ParserExpression)this.then.clone();

        ParserCaseItem res = new ParserCaseItem();
        res.when = when;
        res.then = then;
        return res;
    }

    private ParserExpression when;
    private ParserExpression then;
}
