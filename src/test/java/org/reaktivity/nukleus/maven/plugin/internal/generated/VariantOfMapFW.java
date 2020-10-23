/**
 * Copyright 2016-2020 The Reaktivity Project
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

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.agrona.DirectBuffer;
import org.agrona.MutableDirectBuffer;
import org.reaktivity.reaktor.internal.test.types.Flyweight;
import org.reaktivity.reaktor.internal.test.types.inner.EnumWithInt8;
import org.reaktivity.reaktor.internal.test.types.inner.EnumWithInt8FW;

public final class VariantOfMapFW<K extends Flyweight, V extends Flyweight> extends MapFW<K, V>
{
    public static final EnumWithInt8 KIND_MAP32 = EnumWithInt8.FOUR;

    public static final EnumWithInt8 KIND_MAP16 = EnumWithInt8.FIVE;

    public static final EnumWithInt8 KIND_MAP8 = EnumWithInt8.SIX;

    private final EnumWithInt8FW enumWithInt8RO = new EnumWithInt8FW();

    private final Map32FW<K, V> map32RO;

    private final Map16FW<K, V> map16RO;

    private final Map8FW<K, V> map8RO;

    public VariantOfMapFW(
        K keyType,
        V valueType)
    {
        map32RO = new Map32FW<>(keyType, valueType);
        map16RO = new Map16FW<>(keyType, valueType);
        map8RO = new Map8FW<>(keyType, valueType);
    }

    @Override
    public int length()
    {
        return get().length();
    }

    @Override
    public int fieldCount()
    {
        return get().fieldCount();
    }

    @Override
    public void forEach(
        BiConsumer<K, V> consumer)
    {
        get().forEach(consumer);
    }

    @Override
    public DirectBuffer entries()
    {
        return get().entries();
    }

    public EnumWithInt8 kind()
    {
        return enumWithInt8RO.get();
    }

    public MapFW<K, V> get()
    {
        switch (kind())
        {
        case FOUR:
            return map32RO;
        case FIVE:
            return map16RO;
        case SIX:
            return map8RO;
        default:
            throw new IllegalStateException("Unrecognized kind: " + kind());
        }
    }

    @Override
    public VariantOfMapFW<K, V> tryWrap(
        DirectBuffer buffer,
        int offset,
        int maxLimit)
    {
        if (super.tryWrap(buffer, offset, maxLimit) == null)
        {
            return null;
        }
        final EnumWithInt8FW enumWithInt8 = enumWithInt8RO.tryWrap(buffer, offset, maxLimit);
        if (enumWithInt8 == null || kind() == null)
        {
            return null;
        }
        switch (kind())
        {
        case FOUR:
            if (map32RO.tryWrap(buffer, offset + enumWithInt8.sizeof(), maxLimit) == null)
            {
                return null;
            }
            break;
        case FIVE:
            if (map16RO.tryWrap(buffer, offset + enumWithInt8.sizeof(), maxLimit) == null)
            {
                return null;
            }
            break;
        case SIX:
            if (map8RO.tryWrap(buffer, offset + enumWithInt8.sizeof(), maxLimit) == null)
            {
                return null;
            }
            break;
        default:
            break;
        }
        if (limit() > maxLimit)
        {
            return null;
        }
        return this;
    }

    @Override
    public VariantOfMapFW<K, V> wrap(
        DirectBuffer buffer,
        int offset,
        int maxLimit)
    {
        super.wrap(buffer, offset, maxLimit);
        final EnumWithInt8FW enumWithInt8 = enumWithInt8RO.wrap(buffer, offset, maxLimit);
        switch (kind())
        {
        case FOUR:
            map32RO.wrap(buffer, offset + enumWithInt8.sizeof(), maxLimit);
            break;
        case FIVE:
            map16RO.wrap(buffer, offset + enumWithInt8.sizeof(), maxLimit);
            break;
        case SIX:
            map8RO.wrap(buffer, offset + enumWithInt8.sizeof(), maxLimit);
            break;
        default:
            break;
        }
        checkLimit(limit(), maxLimit);
        return this;
    }

    @Override
    public String toString()
    {
        return get().toString();
    }

    @Override
    public int limit()
    {
        return get().limit();
    }

    public static final class Builder<K extends Flyweight, V extends Flyweight, KB extends Flyweight.Builder<K>,
        VB extends Flyweight.Builder<V>> extends MapFW.Builder<VariantOfMapFW<K, V>, K, V, KB, VB>
    {
        private final EnumWithInt8FW.Builder enumWithInt8RW = new EnumWithInt8FW.Builder();

        private final Map32FW.Builder<K, V, KB, VB> map32RW;

        private final Map16FW.Builder<K, V, KB, VB> map16RW;

        private final Map8FW.Builder<K, V, KB, VB> map8RW;

        public Builder(
            K keyRO,
            V valueRO,
            KB keyRW,
            VB valueRW)
        {
            super(new VariantOfMapFW<>(keyRO, valueRO));
            map32RW = new Map32FW.Builder<>(keyRO, valueRO, keyRW, valueRW);
            map16RW = new Map16FW.Builder<>(keyRO, valueRO, keyRW, valueRW);
            map8RW = new Map8FW.Builder<>(keyRO, valueRO, keyRW, valueRW);
        }

        @Override
        public VariantOfMapFW.Builder<K, V, KB, VB> entry(
            Consumer<KB> key,
            Consumer<VB> value)
        {
            map32RW.entry(key, value);
            limit(map32RW.limit());
            return this;
        }

        @Override
        public VariantOfMapFW.Builder<K, V, KB, VB> entries(
            DirectBuffer buffer,
            int index,
            int length,
            int fieldCount)
        {
            map32RW.entries(buffer, index, length, fieldCount);
            limit(map32RW.limit());
            return this;
        }

        @Override
        public VariantOfMapFW.Builder<K, V, KB, VB> wrap(
            MutableDirectBuffer buffer,
            int offset,
            int maxLimit)
        {
            super.wrap(buffer, offset, maxLimit);
            kind(KIND_MAP32);
            map32RW.wrap(buffer, limit(), maxLimit);
            return this;
        }

        @Override
        public VariantOfMapFW<K, V> build()
        {
            Map32FW map32 = map32RW.build();
            long length = Math.max(map32.length(), map32.fieldCount());
            int highestByteIndex = Long.numberOfTrailingZeros(Long.highestOneBit(length)) >> 3;
            int fieldCount = map32.fieldCount();
            switch (highestByteIndex)
            {
            case 0:
            case 8:
                enumWithInt8RW.wrap(buffer(), offset(), maxLimit());
                enumWithInt8RW.set(KIND_MAP8);
                map8RW.wrap(buffer(), enumWithInt8RW.limit(), maxLimit());
                map8RW.entries(map32.entries(), 0, map32.entries().capacity(), fieldCount);
                limit(map8RW.build().limit());
                break;
            case 1:
                enumWithInt8RW.wrap(buffer(), offset(), maxLimit());
                enumWithInt8RW.set(KIND_MAP16);
                map16RW.wrap(buffer(), enumWithInt8RW.limit(), maxLimit());
                map16RW.entries(map32.entries(), 0, map32.entries().capacity(), fieldCount);
                limit(map16RW.build().limit());
                break;
            case 2:
            case 3:
                limit(map32.limit());
                break;
            default:
                throw new IllegalArgumentException("Illegal length: " + length);
            }
            return super.build();
        }

        private VariantOfMapFW.Builder<K, V, KB, VB> kind(
            EnumWithInt8 value)
        {
            enumWithInt8RW.wrap(buffer(), offset(), maxLimit());
            enumWithInt8RW.set(value);
            limit(enumWithInt8RW.build().limit());
            return this;
        }
    }
}
