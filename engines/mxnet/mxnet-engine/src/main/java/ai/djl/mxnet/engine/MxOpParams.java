/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ai.djl.mxnet.engine;

import ai.djl.Device;
import ai.djl.ndarray.types.DataType;
import ai.djl.ndarray.types.Shape;
import ai.djl.ndarray.types.SparseFormat;
import ai.djl.util.PairList;

/** An internal helper for creating the MXNet operator parameters. */
public class MxOpParams extends PairList<String, Object> {

    // mxnet cpu take index
    private static final String MXNET_CPU = "cpu(0)";

    /**
     * Sets the Shape parameter.
     *
     * @param shape the shape to set
     */
    public void setShape(Shape shape) {
        addParam("shape", shape);
    }

    /**
     * Sets the device to use for the operation.
     *
     * @param device the device to use for the operation
     */
    public void setDevice(Device device) {
        setParam("ctx", ("cpu".equals(device.getDeviceType()) ? MXNET_CPU : device.toString()));
    }

    /**
     * Sets the dataType to use for the operation.
     *
     * @param dataType the dataType to use for the operation
     */
    public void setDataType(DataType dataType) {
        if (dataType != null) {
            setParam("dtype", MxDataType.toMx(dataType));
        }
    }

    /**
     * Sets the sparseFormat to use for the operation.
     *
     * @param sparseFormat the sparseFormat to use for the operation
     */
    public void setSparseFormat(SparseFormat sparseFormat) {
        if (sparseFormat != null) {
            setParam("stype", String.valueOf(sparseFormat.getValue()));
        }
    }

    /**
     * Sets a (potentially existing) parameter to a new value.
     *
     * @param paramName the parameter name to update
     * @param value the value to set the parameter to
     */
    public void setParam(String paramName, String value) {
        remove(paramName);
        add(paramName, value);
    }

    /**
     * Adds a parameter.
     *
     * @param paramName the name of the new parameter
     * @param shape the value of the new parameter
     */
    public void addParam(String paramName, Shape shape) {
        if (shape != null) {
            add(paramName, shape.toString());
        }
    }

    /**
     * Adds a parameter.
     *
     * @param paramName the name of the new parameter
     * @param value the value of the new parameter
     */
    public void addParam(String paramName, String value) {
        add(paramName, value);
    }

    /**
     * Adds a parameter.
     *
     * @param paramName the name of the new parameter
     * @param value the value of the new parameter
     */
    public void addParam(String paramName, int value) {
        add(paramName, String.valueOf(value));
    }

    /**
     * Adds a parameter.
     *
     * @param paramName the name of the new parameter
     * @param value the value of the new parameter
     */
    public void addParam(String paramName, long value) {
        add(paramName, String.valueOf(value));
    }

    /**
     * Adds a parameter.
     *
     * @param paramName the name of the new parameter
     * @param value the value of the new parameter
     */
    public void addParam(String paramName, double value) {
        add(paramName, String.valueOf(value));
    }

    /**
     * Adds a parameter.
     *
     * @param paramName the name of the new parameter
     * @param value the value of the new parameter
     */
    public void addParam(String paramName, float value) {
        add(paramName, String.valueOf(value));
    }

    /**
     * Adds a parameter.
     *
     * @param paramName the name of the new parameter
     * @param value the value of the new parameter
     */
    public void addParam(String paramName, boolean value) {
        add(paramName, value ? "True" : "False");
    }

    /**
     * Adds a parameter.
     *
     * @param paramName the name of the new parameter
     * @param value the value of the new parameter
     */
    public void addParam(String paramName, Number value) {
        add(paramName, String.valueOf(value));
    }

    /**
     * Adds a parameter with tuple value.
     *
     * @param paramName the name of the new parameter
     * @param tuple the values of the new parameter
     */
    public void addTupleParam(String paramName, int... tuple) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int i = 0; i < tuple.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(tuple[i]);
        }
        sb.append(')');
        add(paramName, sb.toString());
    }

    /**
     * Adds a parameter with tuple value.
     *
     * @param paramName the name of the new parameter
     * @param tuple the values of the new parameter
     */
    public void addTupleParam(String paramName, long... tuple) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int i = 0; i < tuple.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(tuple[i]);
        }
        sb.append(')');
        add(paramName, sb.toString());
    }

    /**
     * Adds a parameter with tuple value.
     *
     * @param paramName the name of the new parameter
     * @param tuple the values of the new parameter
     */
    public void addTupleParam(String paramName, float... tuple) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int i = 0; i < tuple.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(tuple[i]);
        }
        sb.append(')');
        add(paramName, sb.toString());
    }
}
