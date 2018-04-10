package nts.ds.ds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Utils
{
    public Utils()
    {
        String letters = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_";
        for (char ch : letters.toCharArray())
        	validLetters.put(ch, ch);
        for (String w : words)
        	keywords.put(w, w);
    }

    
    public static String GetBlobLiteralString(byte[] blob)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("X'");
        for (int i = 0; i < blob.length; i++)
        {
            sb.append(String.format("{0:X2}", blob[i]));
        } // for
        sb.append("'");
        return sb.toString();
    }

    
    public static String QuoteLiteralString(String literal)
    {
        return "'" + literal.replace("'", "''") + "'";
    }
    
    public static String QuoteObjectName(String objName)
    {
        if (objName == null)
            return null;
        objName = Chop(objName);
        objName = objName.replace("[", "\\[");
        objName = objName.replace("]", "\\]");
        return "[" + objName + "]";
    }

    
    public static String QuoteIfNeeded(String objName)
    {
        if (objName == null)
            return null;

        objName = Utils.Chop(objName);
        if (Utils.NeedsQuoting(objName))
            return Utils.QuoteObjectName(objName);
        return objName;
    }

    
    public static String[] QuoteIfNeeded(String[] values)
    {
        if (values == null)
            return null;
        String[] res = new String[values.length];
        for (int i = 0; i < values.length; i++)
            res[i] = Utils.QuoteIfNeeded(values[i]);
        return res;
    }

   
    public static boolean NeedsQuoting(String value)
    {
        if (value == null)
            return false;
        if (value.length() < 2)
            return false;

        for (int i = 0; i < value.length(); i++)
        {
            if (!validLetters.containsKey(value.charAt(i)))
                return true;
        }

        if (Character.isDigit(value.charAt(0)))
            return true;

        // If the string is one of several reserved keywords - it also needs quoting
        String keyword = value.toLowerCase();
        if (keywords.containsKey(keyword))
            return true;

        return false;
    }

    
    public static String Chop(String value)
    {
        if (value == null)
            return null;
        if (value.length() == 0)
            return "";
        if (value.length() == 1)
            return value;
        int last = value.length() - 1;
        if ((value.charAt(0) == '[' || value.charAt(0) == '\"' || value.charAt(0) == '\'' || value.charAt(0) == '`') &&
            (value.charAt(0) == '[' && value.charAt(last) == ']' ||
             value.charAt(0) == value.charAt(last)))
            return value.substring(1, value.length() - 2);
        else
            return value;
    }

    
    public static String[] Chop(String[] list)
    {
        String[] res = new String[list.length];
        for (int i = 0; i < list.length; i++)
        {
            res[i] = Chop(list[i]);
        }

        return res;
    }
 

    public static String GetOperatorString(ParserOperator op)
    {
        switch (op)
        {
            case And:
                return "AND";
            case Or:
                return "OR";
            case Lt:
                return "<";
            case Gt:
                return ">";
            case Ge:
                return ">=";
            case Le:
                return "<=";
            case Eq:
                return "=";
            case Ne:
                return "<>";
            case BitAnd:
                return "&";
            case BitOr:
                return "|";
            case Lshift:
                return "<<";
            case Rshift:
                return ">>";
            case Plus:
                return "+";
            case Minus:
                return "-";
            case Star:
                return "*";
            case Slash:
                return "/";
            case Rem:
                return "%";
            case Concat:
                return "||";            
            case NotNull:
                return "NOTNULL";
            case Is_Null:
                return "IS NULL";
            case Not_Null:
                return "NOT NULL";
            case Is_Not_Null:
                return "IS NOT NULL";
            case Not:
                return "NOT";
            case BitNot:
                return "~";
            default:
                throw new IllegalArgumentException("Illegal sqlite operator [" + op.toString() + "]");
        } // switch
    }

    public static String GetJoinOperatorString(ParserJoinOperator join)
    {
        StringBuilder sb = new StringBuilder();
        if ((join == ParserJoinOperator.Natural))
            sb.append("NATURAL");
        if ((join == ParserJoinOperator.Inner))
            sb.append(" INNER");
        if ((join == ParserJoinOperator.Cross))
            sb.append(" CROSS");
        if ((join == ParserJoinOperator.Left))
            sb.append(" LEFT");
        if ((join == ParserJoinOperator.Outer))//tututut
            sb.append(" OUTER");

        if (sb.length() > 0)
        {
            sb.append(" JOIN");
            return sb.toString();
        }

        return ",";
    }

    public static String GetSelectOperatorString(ParserSelectOperator op)
    {
        switch (op)
        {
            case Union:
                return "UNION";
            case UnionAll:
                return "UNION ALL";
            case Except:
                return "EXCEPT";
            case Intersect:
                return "INTERSECT";
            default:
                throw new IllegalArgumentException("illegal select operator [" + op.toString() + "]");
        } // switch
    }

    public static String FormatTriggerStatementsList(int tab, List<ParserStatement> list)
    {
        StringBuilder tabstr = new StringBuilder();
        for (int i = 0; i < tab; i++)
            tabstr.append(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++)
        {
            ParserStatement stmt = list.get(i);
            String stmtstr = stmt.toString();
            stmtstr = stmtstr.replace("\r\n", "\r\n" + tabstr);
            sb.append(tabstr.toString() + stmtstr + ";\r\n");
        } // for
        return sb.toString();
    }

    private static Map<Character, Character> validLetters = new HashMap<Character, Character>();
    private static Map<String, String> keywords = new HashMap<String, String>();

    private static String[] words = new String[]
        {
            "create",
            "unique",
            "index",
            "if",
            "not",
            "exists",
            "on",
            "collate",
            "asc",
            "desc",
            "table",
            "primary",
            "key",
            "conflict",
            "autoincrement",
            "constraint",
            "rollback",
            "abort",
            "fail",
            "ignore",
            "replace",
            "as",
            "like",
            "glob",
            "regexp",
            "isnull",
            "notnull",
            "between",
            "match",
            "is",
            "escape",
            "in",
            "natural",
            "outer",
            "cross",
            "inner",
            "left",
            "default",
            "null",
            "references",
            "deferrable",
            "cascade",
            "restrict",
            "initially",
            "deferred",
            "immediate",
            "foreign",
            "or",
            "cast",
            "case",
            "raise",
            "begin",
            "end",
            "when",
            "then",
            "trigger",
            "before",
            "after",
            "instead",
            "of",
            "limit",
            "offset",
            "delete",
            "insert",
            "update",
            "for",
            "each",
            "row",
            "set",
            "else",
            "values",
            "into",
            "from",
            "union",
            "all",
            "except",
            "intersect",
            "distinct",
            "join",
            "indexed",
            "by",
            "using",
            "group",
            "order",
            "having",
            "check",
            "view",
            "where",
            "temp",
            "and",
            "temporary"
        };
}
