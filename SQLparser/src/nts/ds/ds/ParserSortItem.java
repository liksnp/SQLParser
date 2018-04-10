package nts.ds.ds;

public class ParserSortItem
{
    private ParserSortItem()
    {
    }

    public ParserSortItem(ParserExpression expr, ParserSortOrder order)
    {
    	this.expr = expr;
    	this.order = order;
    }

    public ParserExpression getExpression()
    {
    	return this.expr;
    }
    
    public void setExpression(ParserExpression expr)
    {
    	this.expr = expr;
    }

    public ParserSortOrder getSortOrder()
    {
    	return this.order;
    }
    
    public void setSortOrder(ParserSortOrder order)
    {
    	this.order = order;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserSortItem dst = (obj instanceof ParserSortItem) ? (ParserSortItem) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.Compare(this.expr, dst.expr))
            return false;

        if (this.order != dst.order)
            return false;

        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(expr.toString());
        if (order == ParserSortOrder.Ascending)
            sb.append(" ASC");
        else if (order == ParserSortOrder.Descending)
            sb.append(" DESC");

        return sb.toString();
    }

    @Override
    public Object clone()
    {
        ParserExpression expr = null;
        if (this.expr != null)
            expr = (ParserExpression)this.expr.clone();

        ParserSortItem res = new ParserSortItem();
        res.expr = expr;
        res.order = this.order;
        return res;
    }

    private ParserExpression expr;
    private ParserSortOrder order;
}
