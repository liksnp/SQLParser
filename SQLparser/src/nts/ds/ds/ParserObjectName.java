package nts.ds.ds;

public class ParserObjectName
{
    public ParserObjectName(String first, String... rest)
    {
    	this.first = Utils.QuoteIfNeeded(first);

        if (rest != null)
        {
            boolean found = false;
            for (String r : rest)
            {
                if (r != null)
                {
                    found = true;
                    break;
                }
            }
            if (found)
            	this.rest = Utils.QuoteIfNeeded(rest);
        }
    }

    public String getFirstName()
    {
        return this.first;
    }

    public String[] getRest()
    {
        return this.rest;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        if (rest != null)
        {
            for (String r : rest)
                sb.append("." + r);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserObjectName dst = (obj instanceof ParserObjectName) ? (ParserObjectName) obj : null;
        if (dst == null)
            return false;

        return dst.toString().toLowerCase().equals(this.toString().toLowerCase());
    }

    @Override
    public int hashCode()
    {
        return this.toString().hashCode();
    }

    @Override
    public Object clone()
    {
        String[] r = null;
        if (this.rest != null)
        {
            r = new String[this.rest.length];
            for(int i=0; i<this.rest.length; i++)
                r[i] = this.rest[i];
        }
        ParserObjectName res = new ParserObjectName(this.first, r);
        return res;
    }

    private String first;
    private String[] rest;
}
