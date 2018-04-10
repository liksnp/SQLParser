package nts.ds.ds;

public class ParserSelectStatement extends ParserStatement {
    
	@Override
	public Object clone()
    {
        return new ParserSelectStatement();
    }
}
