package nts.ds.ds;

public class ParserUnaryExpression extends ParserExpression
{
    private ParserUnaryExpression()
    {
    }

    public ParserUnaryExpression(ParserOperator op, ParserExpression expr)
    {
    	this.op = op;
    	this.expr = expr;
    }

    public ParserOperator getUnaryOperator()
    {
        return this.op;
    }
    
    public void setUnaryOperator(ParserOperator op)
    {
        this.op = op;
    }

    public ParserExpression getExpression()
    {
        return this.expr;
    }
    
    public void setExpression(ParserExpression expr)
    {
        this.expr = expr;
    }

    @Override
    public boolean IsConstant(boolean allowNull)
    {
        return expr.IsConstant(allowNull);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserUnaryExpression dst = (obj instanceof ParserUnaryExpression) ? (ParserUnaryExpression) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.Compare(this.expr, dst.expr))
            return false;
        if (this.op != dst.op)
            return false;

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        String res = null;
        if (op == ParserOperator.Is_Not_Null || op == ParserOperator.Is_Null || 
        	op == ParserOperator.Not_Null || op == ParserOperator.NotNull || op == ParserOperator.Not)
            res = expr.toString() + " " + Utils.GetOperatorString(op) + " ";
        else
            res = /*Utils.GetOperatorString(op) + */ expr.toString();
        return res;
    }

    @Override
    public Object clone()
    {
        ParserExpression expr = null;
        if (this.expr != null)
            expr = (ParserExpression)this.expr.clone();

        ParserUnaryExpression res = new ParserUnaryExpression();
        res.expr = expr;
        res.op = this.op;
        return res;
    }

    private ParserOperator op;
    private ParserExpression expr;
}
