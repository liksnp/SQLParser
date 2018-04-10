package nts.ds.ds;

public class ParserJoinExpression extends ParserExpression {
	private ParserJoinExpression()
    {
    }

    public ParserJoinExpression(ParserExpression left, ParserJoinOperator op, ParserExpression right, ParserExpression condition)
    {
        /*
    	if (left.getClass().getSimpleName() == "SQLiteExpression")
            System.Diagnostics.Debugger.Break();
        */
        this.left = left;
        this.op = op;
        this.right = right;
        this.condition = condition;
    }
    
    public ParserExpression getConditionExpression()
    {
        return this.condition;
    }
    
    public void setConditionExpression(ParserExpression condition)
    {
        /*
    	if (value.getClass().getSimpleName() == "SQLiteExpression")
                System.Diagnostics.Debugger.Break();
        */
        this.condition = condition;
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

    public ParserJoinOperator getBinaryOperator()
    {
        return this.op;
    }
    
    public void setBinaryOperator(ParserJoinOperator op)
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
        /*
    	if (op == SQLiteJoinOperator.Plus || op == SQLiteJoinOperator.Minus ||
            op == SQLiteJoinOperator.Star || op == SQLiteJoinOperator.Slash ||
            op == SQLiteJoinOperator.Rshift || op == SQLiteJoinOperator.Lshift ||
            op == SQLiteJoinOperator.Concat || op == SQLiteJoinOperator.BitAnd ||
            op == SQLiteJoinOperator.BitNot || op == SQLiteJoinOperator.BitOr ||
            op == SQLiteJoinOperator.Rem)
        {
            if (left.IsConstant(allowNull) & right.IsConstant(allowNull))
                return true;                    
        }
        */
        return false;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserJoinExpression dst = (obj instanceof ParserJoinExpression) ? (ParserJoinExpression) obj : null;
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
        if (this.right instanceof ParserTermExpression || 
        		this.right instanceof ParserNameExpression)
            right = this.right.toString();
        else
            right = "(" + this.right.toString() + ")";

        String left;
        if (this.left instanceof ParserIdExpression || this.left instanceof ParserTermExpression ||
        		this.left instanceof ParserNameExpression)
            left = this.left.toString();
        else
            left = "(" + this.left.toString() + ")";
        
        String condition;
        if (this.condition instanceof ParserListExpression)
        	condition = "USING " + this.condition.toString();
        else
        	condition = "ON " + this.condition.toString();

        String res = left + " " + Utils.GetJoinOperatorString(op) + " " + right + " " + condition;
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
        ParserExpression condition = null;
        if (this.condition != null)
        	condition = (ParserExpression)this.condition.clone();

        ParserJoinExpression res = new ParserJoinExpression();
        res.left = left;
        res.op = op;
        res.right = right;
        res.condition = condition;
        return res;
    }
    
    private ParserExpression left;
    private ParserJoinOperator op;
    private ParserExpression right;
    private ParserExpression condition;

}
