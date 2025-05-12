package pers.xia.jpython.interpreter.statement;
import pers.xia.jpython.interpreter.ProgramState;
import pers.xia.jpython.interpreter.expression.Expression;
import pers.xia.jpython.object.PyLong;
import pers.xia.jpython.object.PyObject;

import java.util.List;

public class ForStatement extends BlockStatement {
    private String loopVariable;
    private Expression rangeStart;
    private Expression rangeEnd;
    private Expression rangestep;
    public ForStatement(String loopVariable, Expression rangeStart, Expression rangeEnd,Expression rangestep, List<Statement> body) {
        super(null, body);
        this.loopVariable = loopVariable;
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
        this.rangestep = rangestep;
    }

    @Override
    public void run(ProgramState programState) {
        programState.setVariable(loopVariable, rangeStart.eval(programState));
        for(long i = ((PyLong)programState.getVariable(loopVariable)).asLong(); i < ((PyLong)rangeEnd.eval(programState)).asLong(); i+=((PyLong)rangestep.eval(programState)).asLong()) {
            boolean ifBreak = bodyBlock(programState);
            if(ifBreak){
                return;
            }
            //((PyLong)rangestep.eval(programState)).asLong()
            programState.setVariable(loopVariable, new PyLong(i+((PyLong)rangestep.eval(programState)).asLong()));
        }
        elseBlock(programState);
    }
}
