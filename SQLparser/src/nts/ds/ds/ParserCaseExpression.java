package nts.ds.ds;

import java.util.ArrayList;
import java.util.List;

public class ParserCaseExpression extends ParserExpression
{
    private ParserCaseExpression()
    {
    }

    public ParserCaseExpression(ParserExpression caseOperand, List<ParserCaseItem> caseItems, ParserExpression caseElse)
    {
    	this.caseOperand = caseOperand;
    	this.caseItems = caseItems;
    	this.caseElse = caseElse;
    }

    public ParserExpression getCaseOperand()
    {
        return this.caseOperand;
    }
    
    public void setCaseOperand(ParserExpression caseOperand)
    {
        this.caseOperand = caseOperand;
    }

    public List<ParserCaseItem> getCaseItems()
    {
        return this.caseItems;
    }
    
    public void setCaseItems(List<ParserCaseItem> caseItems)
    {
        this.caseItems = caseItems;
    }

    public ParserExpression getCaseElse()
    {
        return this.caseElse;
    }
    
    public void setCaseElse(ParserExpression caseElse)
    {
        this.caseElse = caseElse;
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

        ParserCaseExpression dst = (obj instanceof ParserCaseExpression) ? (ParserCaseExpression) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.CompareMany(this.caseOperand, dst.caseOperand, this.caseElse, dst.caseElse))
            return false;
        if (!RefCompare.CompareList(this.caseItems, dst.caseItems))
            return false;

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CASE");
        if (caseOperand != null)
            sb.append(" " + caseOperand.toString());
        if (caseItems != null)
        {
            sb.append(" ");
            for (int i = 0; i < caseItems.size(); i++)
            {
                sb.append(caseItems.get(i).toString());
                if (i < caseItems.size() - 1)
                    sb.append(" ");
            } // for
        } // if
        if (caseElse != null)
            sb.append(" ELSE " + caseElse.toString());
        sb.append(" END");

        return sb.toString();
    }

    @Override
    public Object clone()
    {
        ParserExpression caseOperand = null;
        if (this.caseOperand != null)
            caseOperand = (ParserExpression)this.caseOperand.clone();
        List<ParserCaseItem> caseItems = null;
        if (this.caseItems != null)
        {
            caseItems = new ArrayList<ParserCaseItem>();
            for (ParserCaseItem ci : this.caseItems)
                caseItems.add((ParserCaseItem)ci.clone());
        }
        ParserExpression caseElse = null;
        if (this.caseElse != null)
            caseElse = (ParserExpression)this.caseElse.clone();

        ParserCaseExpression res = new ParserCaseExpression();
        res.caseElse = caseElse;
        res.caseItems = caseItems;
        res.caseOperand = caseOperand;
        return res;
    }

    private ParserExpression caseOperand;
    private List<ParserCaseItem> caseItems;
    private ParserExpression caseElse;
}
