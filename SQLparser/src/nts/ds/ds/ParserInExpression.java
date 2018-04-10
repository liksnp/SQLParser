package nts.ds.ds;


public class ParserInExpression extends ParserExpression
{
    private ParserInExpression()
    {
    }

    public ParserInExpression(ParserExpression expr, boolean isIn, ParserListExpression exprlist)
    {
    	this.expr = expr;
    	this.isIn = isIn;
    	this.exprlist = exprlist;
    }

    public ParserInExpression(ParserExpression expr, boolean isIn, ParserSelectStatement select)
    {
    	this.expr = expr;
    	this.isIn = isIn;
    	this.select = select;
    }

    public ParserInExpression(ParserExpression expr, boolean isIn, ParserObjectName table)
    {
    	this.expr = expr;
    	this.isIn = isIn;
    	this.table = table;
    }

    public ParserExpression getExpression()
    {
        return this.expr;
    }
    
    public void setExpression(ParserExpression expr)
    {
        this.expr = expr;
    }

    public boolean getIn()
    {
        return this.isIn;
    }
    
    public void setIn(boolean isIn)
    {
        this.isIn = isIn;
    }

    public ParserListExpression getExpressionsList()
    {
        return this.exprlist;
    }
    
    public void setExpressionsList(ParserListExpression exprlist)
    {
        this.exprlist = exprlist;
    }

    public ParserSelectStatement getSelectStatement()
    {
        return this.select;
    }
    
    public void SelectStatement(ParserSelectStatement select)
    {
        this.select = select;
    }

    public ParserObjectName getTableName()
    {
        return this.table;
    }
    
    public void setTableName(ParserObjectName table)
    {
        this.table = table;
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

        ParserInExpression dst = (obj instanceof ParserInExpression) ? (ParserInExpression) obj : null;
        if (dst == null)
            return false;

        if (this.isIn != dst.isIn)
            return false;

        if (!RefCompare.CompareMany(this.expr, dst.expr, this.select, dst.select, this.table, dst.table))
            return false;        

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(expr.toString());

        if (isIn==true) {
            sb.append(" IN ");
        }else {
            sb.append(" NOT IN ");
        }

        if (exprlist != null)
        {
            sb.append("(");
            sb.append(exprlist.toString());
            sb.append(")");
        } // if
        else if (select != null)
        {
            // expr in_op LP select RP

            sb.append("(");
            sb.append(select.toString());
            sb.append(")");
        } // else
        else if (table != null)
        {
            // expr in_op nm dbnm

            sb.append(table.toString());
        } // else
        else
            throw new IllegalArgumentException("illegal SQLiteInExpression instance");

        return sb.toString();
    }

    @Override
    public Object clone()
    {
        ParserExpression expr = null;
        if (this.expr != null)
            expr = (ParserExpression)this.expr.clone();
        ParserSelectStatement select = null;
        if (this.select != null)
            select = (ParserSelectStatement)this.select.clone();
        ParserObjectName table = null;
        if (this.table != null)
            table = (ParserObjectName)this.table.clone();
        ParserListExpression exprlist = null;
        if (this.exprlist != null)
        {
            exprlist = (ParserListExpression)this.exprlist.clone();
        }

        ParserInExpression res = new ParserInExpression();
        res.expr = expr;
        res.exprlist = exprlist;
        res.isIn = this.isIn;
        res.select = select;
        res.table = table;
        return res;
    }

    private boolean isIn;
    private ParserExpression expr;
    private ParserSelectStatement select;
    private ParserObjectName table;
    private ParserListExpression exprlist;
}
