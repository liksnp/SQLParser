package nts.ds.ds;

public class ParserLikeOperator
{
    private ParserLikeOperator()
    {
    }

    public ParserLikeOperator(ParserLike like, boolean negate)
    {
    	this.like = like;
    	this.negate = negate;
    }

    public ParserLike getLike()
    {
        return this.like;
    }
    
    public void setLike(ParserLike like)
    {
        this.like = like;
    }

    public boolean getNegate()
    {
        return this.negate;
    }
    
    public void setNegate(boolean negate)
    {
        this.negate = negate;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserLikeOperator dst = (obj instanceof ParserLikeOperator) ? (ParserLikeOperator) obj : null;
        if (dst == null)
            return false;

        if (this.like != dst.like || this.negate != dst.negate)
            return false;

        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        if (negate)
            sb.append("NOT ");
        switch (like)
        {
            case Like:
                sb.append("LIKE");
                break;
            case Glob:
                sb.append("GLOB");
                break;
            case Regexp:
                sb.append("REGEXP");
                break;
            case Match:
                sb.append("MATCH");
                break;
            default:
                throw new IllegalArgumentException("illegal like operator [" + like.toString() + "]");
        } // switch

        return sb.toString();
    }

    @Override
    public Object clone()
    {
        ParserLikeOperator res = new ParserLikeOperator();
        res.like = this.like;
        res.negate = this.negate;
        return res;
    }

    private ParserLike like;
    private boolean negate;
}


