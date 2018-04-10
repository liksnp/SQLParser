package nts.ds.ds;

import java.util.ArrayList;
import java.util.List;

public class ParserFunctionExpression extends ParserExpression
{
    private ParserFunctionExpression()
    {
    }

    public ParserFunctionExpression(String id, ParserDistinct distinct, List<ParserExpression> exprlist)
    {
    	this.id = id;
    	this.distinct = distinct;
    	this.exprlist = exprlist;
    }

    public ParserFunctionExpression(String id)
    {
    	this.exprlist = null;
    	this.id = id;
    	this.distinct = ParserDistinct.None;
    }

    public boolean IsCatchAll()
    {
        return this.exprlist == null;
    }

    public String getId()
    {
        return this.id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }

    public ParserDistinct getDistinct()
    {
        return this.distinct;
    }
    
    public void setDistinct(ParserDistinct distinct)
    {
        this.distinct = distinct;
    }

    public List<ParserExpression> getExpressionsList()
    {
        return this.exprlist;
    }
    
    public void setExpressionsList(List<ParserExpression> exprlist)
    {
        this.exprlist = exprlist;
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

        ParserFunctionExpression dst = (obj instanceof ParserFunctionExpression) ? (ParserFunctionExpression) obj : null;
        if (dst == null)
            return false;

        if (this.id != dst.id || this.distinct != dst.distinct)
            return false;

        if (!RefCompare.CompareList(this.exprlist, dst.exprlist))
            return false;

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        String res;
        if (IsCatchAll())
            res = id + "(*)";
        else
        {
            StringBuilder sb = new StringBuilder();
            sb.append(id + "(");
            if (distinct == ParserDistinct.All)
                sb.append("ALL ");
            else if (distinct == ParserDistinct.Distinct)
                sb.append("DISTINCT ");                    
            for (int i = 0; i < exprlist.size(); i++)
            {
                sb.append(exprlist.get(i).toString());
                if (i < exprlist.size() - 1)
                    sb.append(",");
            } // for
            sb.append(")");

            res = sb.toString();
        } // else

        return res;
    }

    @Override
    public Object clone()
    {
        List<ParserExpression> elist = null;
        if (this.exprlist != null)
        {
            elist = new ArrayList<ParserExpression>();
            for (ParserExpression e : this.exprlist)
                elist.add((ParserExpression)e.clone());
        }

        ParserFunctionExpression res = new ParserFunctionExpression();
        res.id = this.id;
        res.distinct = this.distinct;
        res.exprlist = elist;
        return res;
    }

    private String id;
    private ParserDistinct distinct;
    private List<ParserExpression> exprlist;
}
