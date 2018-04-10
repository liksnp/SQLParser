package nts.ds.ds;

import java.util.List;

public class RefCompare
{
    public static boolean Compare(Object t1, Object t2)
    {
        if (t1 == null && t2 != null || t1 != null && t2 == null)
            return false;
        if (t1 == null && t2 == null)
            return true;
        if (!t1.equals(t2))
            return false;
        return true;
    }

    public static boolean CompareMany(Object... args)
    {
        if (args == null || args.length % 2 != 0)
            throw new IllegalArgumentException();

        for (int i = 0; i < args.length; )
        {
            if (!Compare(args[i], args[i + 1]))
                return false;
            i += 2;
        } // for

        return true;
    }

    public static <I extends Object> boolean CompareList(List<I> i1, List<I> i2)
    {
        if (i1 == null && i2 != null || i1 != null && i2 == null)
            return false;
        if (i1 == null && i2 == null)
            return true;
        if (i1.size() != i2.size())
            return false;
        for (int i = 0; i < i1.size(); i++)
        {
            if (i1.get(i) == null && i2.get(i) != null)
                return false;
            if (i1.get(i) != null && i2.get(i) == null)
                return false;
            if (i1.get(i) != null && i2.get(i) != null)
            {
                if (!i1.get(i).equals(i2.get(i)))
                    return false;
            }
        } // for

        return true;
    }
}
