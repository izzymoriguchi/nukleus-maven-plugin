/**
 * Copyright 2016-2019 The Reaktivity Project
 *
 * The Reaktivity Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.reaktivity.nukleus.maven.plugin.internal.generated;

import static java.nio.ByteBuffer.allocateDirect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.junit.Test;
import org.reaktivity.reaktor.internal.test.types.inner.EnumWithInt8;
import org.reaktivity.reaktor.internal.test.types.inner.EnumWithUint32;
import org.reaktivity.reaktor.internal.test.types.inner.VariantEnumKindOfUint32FW;
import org.reaktivity.reaktor.internal.test.types.inner.VariantEnumKindWithString32FW;

public class List8FWTest
{
    private final MutableDirectBuffer buffer = new UnsafeBuffer(allocateDirect(100))
    {
        {
            // Make sure the code is not secretly relying upon memory being initialized to 0
            setMemory(0, capacity(), (byte) 0xab);
        }
    };

    private final List8FW.Builder list8RW = new List8FW.Builder();
    private final List8FW list8RO = new List8FW();
    private final int lengthSize = Byte.BYTES;
    private final int fieldCountSize = Byte.BYTES;

    private void setAllFields(
        MutableDirectBuffer buffer)
    {
        int length = 40;
        int fieldCount = 4;
        int offsetLength = 10;
        buffer.putByte(offsetLength, (byte) length);
        int offsetFieldCount = offsetLength + lengthSize;
        buffer.putByte(offsetFieldCount, (byte) fieldCount);

        int offsetVariantOfString1Kind = offsetFieldCount + fieldCountSize;
        buffer.putByte(offsetVariantOfString1Kind, EnumWithInt8.ONE.value());
        int offsetVariantOfString1Length = offsetVariantOfString1Kind + Byte.BYTES;
        buffer.putByte(offsetVariantOfString1Length, (byte) "string1".length());
        int offsetVariantOfString1 = offsetVariantOfString1Length + Byte.BYTES;
        buffer.putBytes(offsetVariantOfString1, "string1".getBytes());

        int offsetVariantOfString2Kind = offsetVariantOfString1 + "string1".length();
        buffer.putByte(offsetVariantOfString2Kind, EnumWithInt8.ONE.value());
        int offsetVariantOfString2Length = offsetVariantOfString2Kind + Byte.BYTES;
        buffer.putByte(offsetVariantOfString2Length, (byte) "string2".length());
        int offsetVariantOfString2 = offsetVariantOfString2Length + Byte.BYTES;
        buffer.putBytes(offsetVariantOfString2, "string2".getBytes());

        int offsetVariantOfUintKind = offsetVariantOfString2 + "string2".length();
        buffer.putLong(offsetVariantOfUintKind, EnumWithUint32.NI.value());
        int offsetVariantOfUint = offsetVariantOfUintKind + Long.BYTES;
        buffer.putLong(offsetVariantOfUint, 4000000000L);

        int offsetVariantOfIntKind = offsetVariantOfUint + Long.BYTES;
        buffer.putByte(offsetVariantOfIntKind, EnumWithInt8.THREE.value());
        int offsetVariantOfInt = offsetVariantOfIntKind + Byte.BYTES;
        buffer.putInt(offsetVariantOfInt, -2000000000);
    }

    @Test
    public void shouldNotWrapWhenLengthInsufficientForMinimumRequiredLength()
    {
        int length = 40;
        setAllFields(buffer);
        for (int maxLimit = 10; maxLimit <= length; maxLimit++)
        {
            try
            {
                list8RO.wrap(buffer,  10, maxLimit);
                fail("Exception not thrown");
            }
            catch (Exception e)
            {
                if (!(e instanceof IndexOutOfBoundsException))
                {
                    fail("Unexpected exception " + e);
                }
            }
        }
    }

    @Test
    public void shouldNotTryWrapWhenLengthInsufficientForMinimumRequiredLength()
    {
        int length = 40;
        int offsetLength = 10;
        setAllFields(buffer);
        for (int maxLimit = 10; maxLimit <= length; maxLimit++)
        {
            assertNull(list8RO.tryWrap(buffer,  offsetLength, maxLimit));
        }
    }

    @Test
    public void shouldWrapWhenLengthSufficientForMinimumRequiredLength()
    {
        int length = 40;
        int fieldCount = 4;
        int offsetLength = 10;
        int maxLimit = offsetLength + lengthSize + length;
        setAllFields(buffer);

        assertSame(list8RO, list8RO.wrap(buffer, offsetLength, maxLimit));
        assertEquals(length, list8RO.length());
        assertEquals(fieldCount, list8RO.fieldCount());
        assertEquals(length - fieldCountSize, list8RO.fields().capacity());
        assertEquals(maxLimit, list8RO.limit());
    }

    @Test
    public void shouldTryWrapWhenLengthSufficientForMinimumRequiredLength()
    {
        int length = 40;
        int fieldCount = 4;
        int offsetLength = 10;
        int maxLimit = offsetLength + lengthSize + length;
        setAllFields(buffer);

        assertSame(list8RO, list8RO.tryWrap(buffer, offsetLength, maxLimit));
        assertEquals(length, list8RO.length());
        assertEquals(fieldCount, list8RO.fieldCount());
        assertEquals(length - fieldCountSize, list8RO.fields().capacity());
        assertEquals(maxLimit, list8RO.limit());
    }

    @Test
    public void shouldSetFieldsUsingFieldsMethodWithDirectBuffer() throws Exception
    {
        final MutableDirectBuffer listBuffer = new UnsafeBuffer(allocateDirect(100))
        {
            {
                // Make sure the code is not secretly relying upon memory being initialized to 0
                setMemory(0, capacity(), (byte) 0xab);
            }
        };
        VariantEnumKindWithString32FW.Builder field1RW = new VariantEnumKindWithString32FW.Builder();
        VariantEnumKindOfUint32FW.Builder field2RW = new VariantEnumKindOfUint32FW.Builder();
        ListFW.Builder listRW = new List8FW.Builder()
            .wrap(listBuffer, 0, listBuffer.capacity())
            .field((b, o, m) -> field1RW.wrap(b, o, m).set("string1").build().sizeof())
            .field((b, o, m) -> field2RW.wrap(b, o, m).set(4000000000L).build().sizeof());
        List8FW list8RO = (List8FW) listRW.build();
        int limit = list8RW.wrap(buffer, 0, buffer.capacity())
            .fields(2, list8RO.buffer(), 0, list8RO.length() - fieldCountSize)
            .build()
            .limit();
        list8RO.wrap(buffer,  0,  limit);
        assertEquals(26, list8RO.length());
        assertEquals(2, list8RO.fieldCount());
        assertEquals(27, list8RO.limit());
    }

    @Test
    public void shouldSetFieldsUsingFieldsMethodWithVisitor() throws Exception
    {
        final MutableDirectBuffer listBuffer = new UnsafeBuffer(allocateDirect(100))
        {
            {
                // Make sure the code is not secretly relying upon memory being initialized to 0
                setMemory(0, capacity(), (byte) 0xab);
            }
        };
        VariantEnumKindWithString32FW.Builder field1RW = new VariantEnumKindWithString32FW.Builder();
        VariantEnumKindOfUint32FW.Builder field2RW = new VariantEnumKindOfUint32FW.Builder();
        ListFW.Builder listRW = new List8FW.Builder()
            .wrap(listBuffer, 0, listBuffer.capacity())
            .field((b, o, m) -> field1RW.wrap(b, o, m).set("string1").build().sizeof())
            .field((b, o, m) -> field2RW.wrap(b, o, m).set(4000000000L).build().sizeof());
        List8FW list8RO = (List8FW) listRW.build();

        int limit = list8RW.wrap(buffer, 0, buffer.capacity())
            .fields(2, (b, o, m) ->
            {
                b.putBytes(o, list8RO.fields(), 0, list8RO.fields().capacity());
                return list8RO.fields().capacity();
            })
            .build()
            .limit();
        list8RO.wrap(buffer,  0,  limit);
        assertEquals(26, list8RO.length());
        assertEquals(2, list8RO.fieldCount());
        assertEquals(27, list8RO.limit());
    }

    @Test
    public void shouldSetFieldsUsingFieldMethod() throws Exception
    {
        VariantEnumKindWithString32FW.Builder field1RW = new VariantEnumKindWithString32FW.Builder();
        VariantEnumKindOfUint32FW.Builder field2RW = new VariantEnumKindOfUint32FW.Builder();
        int limit = list8RW.wrap(buffer, 0, buffer.capacity())
            .field((b, o, m) -> field1RW.wrap(b, o, m).set("string1").build().sizeof())
            .field((b, o, m) -> field2RW.wrap(b, o, m).set(4000000000L).build().sizeof())
            .build()
            .limit();
        list8RO.wrap(buffer,  0,  limit);
        assertEquals(26, list8RO.length());
        assertEquals(2, list8RO.fieldCount());
        assertEquals(27, list8RO.limit());
    }
}
