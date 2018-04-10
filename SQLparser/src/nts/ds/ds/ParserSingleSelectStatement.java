package nts.ds.ds;

import java.util.ArrayList;
import java.util.List;

public class ParserSingleSelectStatement extends ParserSelectStatement {
	private ParserSingleSelectStatement()
    {
    }

    public ParserSingleSelectStatement(ParserDistinct distinct, List<ParserSelectColumn> columns, 
        ParserFromClause from, ParserExpression whereExpr, List<ParserExpression> groupBy,
        ParserExpression having, List<ParserSortItem> orderBy, ParserLimitClause limit)
    {
        this.distinct = distinct;
        this.columns = columns;
        this.from = from;
        this.whereExpr = whereExpr;
        this.groupBy = groupBy;
        this.having = having;
        this.orderBy = orderBy;
        this.limit = limit;
    }

    public ParserDistinct getDistinct(){
    	return this.distinct;
    }
    
    public void setDistinct(ParserDistinct distinct){
    	this.distinct = distinct;
    }
    
    public List<ParserSelectColumn> getColumns(){
    	return this.columns;
    }
    
    public void setColumns(List<ParserSelectColumn> columns)
    {
        this.columns = columns;
    }

    public ParserFromClause getFromClause()
    {
    	return this.from;
    }

    public void setFromClause(ParserFromClause from)
    {
        this.from = from;
    }
    
    public ParserExpression getWhereExpression()
    {
    	return this.whereExpr;
    }

    public void setWhereExpression(ParserExpression whereExpr)
    {
        this.whereExpr = whereExpr;
    }

    public List<ParserExpression> getGroupBy()
    {
    	return this.groupBy;
    }
    
    public void setGroupBy(List<ParserExpression> groupBy)
    {
        this.groupBy = groupBy;
    }

    public ParserExpression getHavingExpression()
    {
    	return this.having;
    }
    
    public void setHavingExpression(ParserExpression having)
    {
        this.having = having;
    }

    public List<ParserSortItem> getOrderBy()
    {
        return this.orderBy;
    }
    
    public void setOrderBy(List<ParserSortItem> orderBy)
    {
    	this.orderBy = orderBy;
    }

    public ParserLimitClause getLimitClause()
    {
        return this.limit;
    }
    
    public void setLimitClause(ParserLimitClause limit)
    {
        this.limit = limit;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserSingleSelectStatement dst = (obj instanceof ParserSingleSelectStatement) ? (ParserSingleSelectStatement)obj : null;
        if (dst == null)
            return false;

        if (this.distinct != dst.distinct)
            return false;

        if (!RefCompare.CompareMany(this.from, dst.from, this.whereExpr, dst.whereExpr, this.having, dst.having,
            this.limit, dst.limit))
            return false;
        if (!RefCompare.CompareList(this.columns, dst.columns))
            return false;
        if (!RefCompare.CompareList(this.groupBy, dst.groupBy))
            return false;
        if (!RefCompare.CompareList(this.orderBy, dst.orderBy))
            return false;

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        // SELECT distinct selcollist from where_opt groupby_opt having_opt orderby_opt limit_opt

        sb.append("SELECT");
        if (this.distinct == ParserDistinct.Distinct)
            sb.append(" DISTINCT");
        else if (this.distinct == ParserDistinct.All)
            sb.append(" ALL");

        sb.append(" ");
        for (int i = 0; i < columns.size(); i++)
        {
            sb.append(this.columns.get(i).toString());
            if (i < columns.size() - 1)
                sb.append(",");
        } // for

        if (from != null)
            sb.append("\r\n    FROM " + from.toString());

        if (whereExpr != null)
            sb.append("\r\n    WHERE " + whereExpr.toString());

        if (groupBy != null)
        {
            sb.append("\r\n    GROUP BY ");
            for (int i = 0; i < groupBy.size(); i++)
            {
                sb.append(groupBy.get(i).toString());
                if (i < groupBy.size() - 1)
                    sb.append(",");
            } // for
        }

        if (having != null)
            sb.append("\r\n    HAVING " + having.toString());

        if (orderBy != null)
        {
            sb.append("\r\n    ORDER BY ");
            for (int i = 0; i < orderBy.size(); i++)
            {
                sb.append(orderBy.get(i).toString());
                if (i < orderBy.size() - 1)
                    sb.append(",");
            } // for
        } // if

        if (limit != null)
            sb.append("\r\n    " + limit.toString());

        return sb.toString();
    }

    @Override
    public Object clone()
    {
        ParserFromClause from = null;
        if (this.from != null)
            from = (ParserFromClause)this.from.clone();
        ParserExpression whereExpr = null;
        if (this.whereExpr != null)
            whereExpr = (ParserExpression)this.whereExpr.clone();
        ParserExpression having = null;
        if (this.having != null)
            having = (ParserExpression)this.having.clone();
        ParserLimitClause limit = null;
        if (this.limit != null)
            limit = (ParserLimitClause)this.limit.clone();
        List<ParserSelectColumn> columns = null;
        if (this.columns != null)
        {
            columns = new ArrayList<ParserSelectColumn>();
            for (ParserSelectColumn c : this.columns)
                columns.add((ParserSelectColumn)c.clone());
        }
        List<ParserExpression> groupBy = null;
        if (this.groupBy != null)
        {
            groupBy = new ArrayList<ParserExpression>();
            for (ParserExpression e : this.groupBy)
                groupBy.add((ParserExpression)e.clone());
        }
        List<ParserSortItem> orderBy = null;
        if (this.orderBy != null)
        {
            orderBy = new ArrayList<ParserSortItem>();
            for (ParserSortItem i : orderBy)
                orderBy.add((ParserSortItem)i.clone());
        }

        ParserSingleSelectStatement res = new ParserSingleSelectStatement();
        res.distinct = distinct;
        res.from = from;
        res.whereExpr = whereExpr;
        res.having = having;
        res.limit = limit;
        res.columns = columns;
        res.groupBy = groupBy;
        res.orderBy = orderBy;
        return res;
    }

    private ParserDistinct distinct;        
    private ParserFromClause from;//+
    private ParserExpression whereExpr;//+
    private ParserExpression having;
    private ParserLimitClause limit;
    private List<ParserSelectColumn> columns;//+
    private List<ParserExpression> groupBy;//+
    private List<ParserSortItem> orderBy;//+
}
