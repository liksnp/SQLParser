package nts.ds.ds;

import com.sun.istack.internal.Nullable;
//import javax.annotation.Nullable;

public class ParserTerm
{
    private ParserTerm()
    {
    }

    public ParserTerm(String str)
    {
    	this.str = str;
    }

    public ParserTerm(double number)
    {
    	this.number = number;
    }

    public ParserTerm(ParserTimeFunction time)
    {
    	this.time = time;
    }

    public ParserTerm(byte[] blob)
    {
    	this.blob = blob;
    }

    public String getAsString()
    {
        return this.str;
    }
    
    public void setAsString(String str)
    {
        this.str = str;
    }

    @Nullable public double getAsNumber()
    {
        return this.number;
    }
    
    public void setAsNumber(@Nullable double number)
    {
    	this.number = number;
    }

    @Nullable public ParserTimeFunction getAsTimeFunction()
    {
        return this.time;
    }
    
    public void setAsTimeFunction(@Nullable ParserTimeFunction time)
    {
    	this.time = time;
    }

    public byte[] getAsBlob()
    {
        return this.blob;
    }
    
    public void setAsBlob(byte[] blob)
    {
        this.blob = blob;
    }

    public static byte[] CreateBlob(String blobLiteral)
    {
        int len = blobLiteral.length() - 3;
        byte[] res = new byte[len / 2];
        for (int i = 0; i < res.length; i++)
        {
            char ch1 = blobLiteral.charAt(i * 2 + 2);
            char ch2 = blobLiteral.charAt(i * 2 + 3);
            String str = "" + ch1 + ch2;
            res[i] = Byte.parseByte(str, 16);
        } // for
        return res;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserTerm dst = (obj instanceof ParserTerm) ? (ParserTerm) obj : null;
        if (dst == null)
            return false;

        if (this.str != dst.str)
            return false;
        if (this.number != dst.number)
            return false;
        if (this.time != dst.time)
            return false;
        if (this.blob == null && dst.blob != null ||
            this.blob != null && dst.blob == null)
            return false;
        if (this.blob == null && dst.blob == null)
            return true;
        if (this.blob.length != dst.blob.length)
            return false;
        for (int i = 0; i < this.blob.length; i++)
        {
            if (this.blob[i] != dst.blob[i])
                return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        if (str != null)
            return str;
        if (number != null)
            return number.toString();
        if (time != null)
            return GetTimeFunctionName(time);
        if (blob != null)
            return Utils.GetBlobLiteralString(blob);
        throw new IllegalStateException("The SQLiteTerm object is not initialized");
    }

    @Override
    public Object clone()
    {
        ParserTerm res = new ParserTerm();
        res.str = this.str;
        res.number = this.number;
        res.time = this.time;
        if (this.blob != null)
        {
            res.blob = new byte[this.blob.length];
            for (int i = 0; i < this.blob.length; i++)
                res.blob[i] = this.blob[i];
        }
        return res;
    }

    private String GetTimeFunctionName(ParserTimeFunction func)
    {
        switch (func)
        {
            case CurrentTime:
                return "CURRENT_TIME";
            case CurrentDate:
                return "CURRENT_DATE";
            case CurrentTimestamp:
                return "CURRENT_TIMESTAMP";
            default:
                throw new IllegalArgumentException("Illegal function enum value [" + func.toString() + "]");
        } // switch
    }

    private String str;
    private @Nullable Double number;
    private @Nullable ParserTimeFunction time;
    private byte[] blob;
}


