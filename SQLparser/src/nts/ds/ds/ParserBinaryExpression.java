package nts.ds.ds;

public class ParserBinaryExpression extends ParserExpression
{
    private ParserBinaryExpression()
    {
    }

    public ParserBinaryExpression(ParserExpression left, ParserOperator op, ParserExpression right)
    {
        /*
    	if (left.getClass().getSimpleName() == "SQLiteExpression")
            System.Diagnostics.Debugger.Break();
        */
        this.left = left;
        this.op = op;
        this.right = right;
    }
    

    public ParserExpression getLeftExpression()
    {
        return this.left;
    }
    
    public void setLeftExpression(ParserExpression left)
    {
        /*
    	if (value.getClass().getSimpleName() == "SQLiteExpression")
                System.Diagnostics.Debugger.Break();
        */
        this.left = left;
    }

    public ParserOperator getBinaryOperator()
    {
        return this.op;
    }
    
    public void setBinaryOperator(ParserOperator op)
    {
        this.op = op;
    }

    public ParserExpression getRightExpression()
    {
        return this.right;
    }
    
    public void setRightExpression(ParserExpression right)
    {
        this.right = right;
    }

    @Override
    public boolean IsConstant(boolean allowNull)
    {
        if (op == ParserOperator.Plus || op == ParserOperator.Minus ||
            op == ParserOperator.Star || op == ParserOperator.Slash ||
            op == ParserOperator.Rshift || op == ParserOperator.Lshift ||
            op == ParserOperator.Concat || op == ParserOperator.BitAnd ||
            op == ParserOperator.BitNot || op == ParserOperator.BitOr ||
            op == ParserOperator.Rem)
        {
            if (left.IsConstant(allowNull) & right.IsConstant(allowNull))
                return true;                    
        }
        return false;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserBinaryExpression dst = (obj instanceof ParserBinaryExpression) ? (ParserBinaryExpression) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.CompareMany(this.left, dst.left, this.right, dst.right))
            return false;

        if (this.op != dst.op)
            return false;

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        String right;
        if (this.right instanceof ParserIdExpression || this.right instanceof ParserTermExpression || 
        		this.right instanceof ParserNameExpression || IsEqualityOperator(op))
            right = this.right.toString();
        else
            right = "(" + this.right.toString() + ")";

        String left;
        if (this.left instanceof ParserIdExpression || this.left instanceof ParserTermExpression ||
        		this.left instanceof ParserNameExpression || IsEqualityOperator(op))
            left = this.left.toString();
        else
            left = "(" + this.left.toString() + ")";

        String res = left + " " + Utils.GetOperatorString(op) + " " + right;
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
        /*
        if (left.getClass().getSimpleName() == "SQLiteExpression")
            System.Diagnostics.Debugger.Break();
        */
        ParserBinaryExpression res = new ParserBinaryExpression();
        res.left = left;
        res.op = op;
        res.right = right;
        return res;
    }

    private boolean IsEqualityOperator(ParserOperator op)
    {
        switch (op)
        {
            case Lt:
            case Gt:
            case Ge:
            case Le:
            case Eq:
            case Ne:
                return true;
            default:
                return false;
        } // switch
    }

    private ParserExpression left;
    private ParserOperator op;
    private ParserExpression right;
}
