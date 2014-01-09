package org.gla.anton.udf.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pig.EvalFunc;
import org.apache.pig.FuncSpec;
import org.apache.pig.PigWarning;
import org.apache.pig.data.DataType;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.logicalLayer.FrontendException;
import org.apache.pig.impl.logicalLayer.schema.Schema;

public class GenerateVenueTweet extends EvalFunc<String> {

    /**
     * Method invoked on every tuple during foreach evaluation
     * @param input tuple; first column is assumed to have the column to convert
     * @exception java.io.IOException
     */
    @Override
    public String exec(Tuple input) throws IOException {
        if (input == null || input.size() < 1) {
            warn("invalid number of arguments to GenerateVenueTweet", PigWarning.UDF_WARNING_1);
            return null;
        }
        try {
            String source = (String)input.get(0);
            Integer beginindex = (Integer)input.get(1);
            Integer endindex = (Integer)input.get(2);
            return source.substring(beginindex, Math.min(source.length(), endindex));
        } catch (NullPointerException npe) {
            warn(npe.toString(), PigWarning.UDF_WARNING_2);
            return null;
        } catch (StringIndexOutOfBoundsException npe) {
            warn(npe.toString(), PigWarning.UDF_WARNING_3);
            return null;
        } catch (ClassCastException e) {
            warn(e.toString(), PigWarning.UDF_WARNING_4);
            return null;
        }
    }

    @Override
    public Schema outputSchema(Schema input) {
        return new Schema(new Schema.FieldSchema(null, DataType.CHARARRAY));
    }

    /* (non-Javadoc)
     * @see org.apache.pig.EvalFunc#getArgToFuncMapping()
     */
    @Override
    public List<FuncSpec> getArgToFuncMapping() throws FrontendException {
        List<FuncSpec> funcList = new ArrayList<FuncSpec>();
        Schema s = new Schema();
        s.add(new Schema.FieldSchema(null, DataType.CHARARRAY));
        s.add(new Schema.FieldSchema(null, DataType.INTEGER));
        s.add(new Schema.FieldSchema(null, DataType.INTEGER));
        funcList.add(new FuncSpec(this.getClass().getName(), s));
        return funcList;
    }
}
