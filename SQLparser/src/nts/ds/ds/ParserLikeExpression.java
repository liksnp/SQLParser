package nts.ds.ds;

public class ParserLikeExpression extends ParserExpression
{
    private ParserLikeExpression()
    {
    }

    public ParserLikeExpression(ParserExpression left, ParserLikeOperator likeOp, ParserExpression right, ParserExpression escape)
    {
    	this.left = left;
    	this.likeOp = likeOp;
    	this.right = right;
    	this.escape = escape;
    }

    public ParserExpression getLeftExpression()
    {
        return this.left;
    }
    
    public void setLeftExpression(ParserExpression left)
    {
        this.left = left;
    }

    public ParserLikeOperator getLikeOperator()
    {
        return this.likeOp;
    }
    
    public void setLikeOperator(ParserLikeOperator likeOp)
    {
        this.likeOp = likeOp;
    }

    public ParserExpression getRightExpression()
    {
        return this.right;
    }
    
    public void setRightExpression(ParserExpression right)
    {
        this.right = right;
    }

    public ParserExpression getEscapeExpression()
    {
        return this.escape;
    }
    
    public void EscapeExpression(ParserExpression escape)
    {
        this.escape = escape;
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

        ParserLikeExpression dst = (obj instanceof ParserLikeExpression) ? (ParserLikeExpression) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.CompareMany(this.likeOp, dst.likeOp, this.left, dst.left, this.right, dst.right, this.escape, dst.escape))
            return false;

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        // expr likeop expr escape

        StringBuilder sb = new StringBuilder();
        sb.append(left.toString() + " " + likeOp.toString() + " " + right.toString());
        if (escape != null)
            sb.append(" ESCAPE " + escape.toString());

        return sb.toString();
    }

    @Override
    public Object clone()
    {
        ParserLikeOperator likeOp = null;
        if (this.likeOp != null)
            likeOp = (ParserLikeOperator)this.likeOp.clone();
        ParserExpression left = null;
        if (this.left != null)
            left = (ParserExpression)this.left.clone();
        ParserExpression right = null;
        if (this.right != null)
            right = (ParserExpression)this.right.clone();
        ParserExpression escape = null;
        if (this.escape != null)
            escape = (ParserExpression)this.escape.clone();

        ParserLikeExpression res = new ParserLikeExpression();
        res.likeOp = likeOp;
        res.left = left;
        res.right = right;
        res.escape = escape;
        return res;
    }

    private ParserLikeOperator likeOp;
    private ParserExpression left;
    private ParserExpression right;
    private ParserExpression escape;
}
