package nts.ds.ds;

public class ParserBetweenExpression extends ParserExpression
{
    public ParserBetweenExpression()
    {
    }

    public ParserBetweenExpression(ParserExpression left, boolean between, ParserExpression right, ParserExpression and)
    {
    	this.left = left;
    	this.between = between;
    	this.right = right;
    	this.and = and;
    }

    public ParserExpression getLeftExpression()
    {
        return this.left;
    }
    
    public void setLeftExpression(ParserExpression left)
    {
        this.left = left;
    }

    public boolean getIsBetween()
    {
        return this.between;
    }
    
    public void setIsBetween(boolean between)
    {
        this.between = between;
    }

    public ParserExpression getRightExpression()
    {
        return this.right;
    }
    
    public void setRightExpression(ParserExpression right)
    {
        this.right = right;
    }

    public ParserExpression getAndExpression()
    {
        return this.and;
    }
    
    public void setAndExpression(ParserExpression and)
    {
        this.and = and;
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

        ParserBetweenExpression dst = (obj instanceof ParserBetweenExpression) ? (ParserBetweenExpression) obj : null;
        if (dst == null)
            return false;

        if (this.between != dst.between)
            return false;

        if (!RefCompare.CompareMany(this.left, dst.left, this.right, dst.right, this.and, dst.and))
            return false;

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        String res = left.toString() + ((between==true) ? " " : " NOT") + " BETWEEN " + right.toString() + " AND " + and.toString();
        return res;
    }

    @Override
    public Object clone()
    {
        ParserExpression left = null;
        if (this.left != null)
            left = (ParserExpression)this.left.clone();
        ParserExpression right = null;
        if (this.right != null)
            right = (ParserExpression)this.right.clone();
        ParserExpression and = null;
        if (this.and != null)
            and = (ParserExpression)this.and.clone();

        ParserBetweenExpression res = new ParserBetweenExpression();
        res.and = and;
        res.between = this.between;
        res.left = left;
        res.right = right;
        return res;
    }

    private ParserExpression left;
    private boolean between;
    private ParserExpression right;
    private ParserExpression and;
}
