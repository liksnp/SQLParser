package nts.ds.ds;

import java.util.ArrayList;
import java.util.List;

public class ParserFromTable
{
    private ParserFromTable()
    {
    }

    public ParserFromTable(ParserObjectName table, String asName, 
        ParserFromIndexed indexed, ParserExpression onExpr, List<String> usingOpt)
    {
    	this.table = table;
        setAsName(asName);
        this.indexed = indexed;
        this.onExpr = onExpr;
        this.usingOpt = usingOpt;
    }

    public ParserFromTable(ParserFromInternalTable itable, String asName,
        ParserExpression onExpr, List<String> usingOpt)
    {            
    	this.itable = itable;
    	this.setAsName(asName);
        this.onExpr = onExpr;
        this.usingOpt = usingOpt;
    }

    public ParserFromInternalTable getInternalTable()
    {
        return this.itable;
    }
    
    public void setInternalTable(ParserFromInternalTable itable)
    {
    	this.itable = itable;
    }

    public ParserObjectName getTableName()
    {
    	return this.table;
    }
    
    public void setTableName(ParserObjectName table)
    {
    	this.table = table;
    }

    public String getAsName()
    {
        return this.asName;
    }
    
    public void setAsName(String asName)
    {
        this.asName = Utils.QuoteIfNeeded(asName);
    }

    public ParserFromIndexed getIndexed()
    {
        return this.indexed;
    }
    
    public void Indexed(ParserFromIndexed indexed)
    {
    	this.indexed = indexed;
    }

    public ParserExpression getOnExpression()
    {
        return this.onExpr;
    }
    
    public void setOnExpression(ParserExpression onExpr)
    {
    	this.onExpr = onExpr; 
    }

    public List<String> getUsingOpt()
    {
        return this.usingOpt;
    }
    
    public void setUsingOpt(List<String> usingOpt)
    {
    	this.usingOpt = usingOpt;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserFromTable dst = (obj instanceof ParserFromTable) ? (ParserFromTable) obj : null;
        if (dst == null)
            return false;

        if (this.asName != dst.asName)
            return false;

        if (!RefCompare.CompareMany(this.table, dst.table, this.itable, dst.itable, this.indexed, dst.indexed,
            this.onExpr, dst.onExpr))
            return false;
        if (!RefCompare.CompareList(this.usingOpt, dst.usingOpt))
            return false;

        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();            

        if (itable != null)
        {
            // LP seltablist_paren RP as on_opt using_opt
            sb.append(" ("+itable.toString()+") ");
        }
        else
        {
            // nm dbnm as indexed_opt on_opt using_opt            
            sb.append(" " + table.toString());
        } // else

        if (asName != null)
            sb.append(" AS " + asName);
        if (indexed != null)
            sb.append(" " + indexed);
        if (onExpr != null)
            sb.append(" ON " + onExpr.toString());
        if (usingOpt != null && usingOpt.size() > 0)
        {
            sb.append(" USING (");
            for (int i = 0; i < usingOpt.size(); i++)
            {
                sb.append(usingOpt.get(i));
                if (i < usingOpt.size() - 1)
                    sb.append(",");
            } // for
            sb.append(")");
        }
        
        return sb.toString();
    }

    @Override
    public Object clone()
    {
        ParserObjectName t = null;
        if (this.table != null)
            t = (ParserObjectName)this.table.clone();
        ParserFromInternalTable fit = null;
        if (this.itable != null)
            fit = (ParserFromInternalTable)this.itable.clone();
        ParserFromIndexed fi = null;
        if (this.indexed != null)
            fi = (ParserFromIndexed)this.indexed.clone();
        ParserExpression expr = null;
        if (this.onExpr != null)
            expr = (ParserExpression)this.onExpr.clone();
        List<String> us = null;
        if (this.usingOpt != null)
        {
            us = new ArrayList<String>();
            for (String str : this.usingOpt)
                us.add(str);
        }

        ParserFromTable res = new ParserFromTable();
        res.asName = this.asName;
        res.indexed = fi;
        res.itable = fit;
        res.onExpr = expr;
        res.table = t;
        res.usingOpt = us;           
        return res;
    }

    private String asName;
    private ParserObjectName table;
    private ParserFromInternalTable itable;
    private ParserFromIndexed indexed;
    private ParserExpression onExpr;
    private List<String> usingOpt;        
}
