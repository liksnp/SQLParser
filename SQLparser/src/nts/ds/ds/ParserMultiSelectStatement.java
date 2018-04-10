package nts.ds.ds;

public class ParserMultiSelectStatement extends ParserSelectStatement
{
    private ParserMultiSelectStatement()
    {
    }

    public ParserMultiSelectStatement(ParserSelectStatement first, ParserSelectOperator op, ParserSelectStatement next)
    {
    	this.first = first;
    	this.op = op;
    	this.next = next;
    }

    public ParserSelectStatement getFirst()
    {
        return this.first;
    }
    
    public void setFirst(ParserSelectStatement first)
    {
        this.first = first;
    }

    public ParserSelectOperator getSelectOperator()
    {
        return this.op;
    }
    
    public void setSelectOperator(ParserSelectOperator op)
    {
        this.op = op;
    }

    public ParserSelectStatement getNext()
    {
        return this.next;
    }
    
    public void setNext(ParserSelectStatement next)
    {
        this.next = next;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserMultiSelectStatement dst = (obj instanceof ParserMultiSelectStatement) ? (ParserMultiSelectStatement) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.CompareMany(this.first, dst.first, this.next, dst.next))
            return false;

        if (this.op != dst.op)
            return false;

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        return first.toString() + "\r\n" + Utils.GetSelectOperatorString(op) + "\r\n" + next.toString();
    }

    @Override
    public Object clone()
    {
        ParserSelectStatement first = null;
        if (this.first != null)
            first = (ParserSelectStatement)this.first.clone();
        ParserSelectStatement next = null;
        if (this.next != null)
            next = (ParserSelectStatement)this.next.clone();

        ParserMultiSelectStatement res = new ParserMultiSelectStatement();
        res.first = first;
        res.op = this.op;
        res.next = next;
        return res;
    }

    private ParserSelectStatement first;
    private ParserSelectOperator op;
    private ParserSelectStatement next;
}