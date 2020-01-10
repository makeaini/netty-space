package com.pulsar.netty.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.pulsar.netty.domain.MsgInfo;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class SerializationUtil<T> {

	private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

	private static <T> Schema<T> getSchema(Class<T> cls) {
		Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
		if (schema == null) {
			schema = RuntimeSchema.createFrom(cls);
			if (schema != null) {
				cachedSchema.put(cls, schema);
			}
		}
		return schema;
	}

	/**
	 * 序列化
	 *
	 * @param obj
	 * @param <T>
	 * @return
	 */
	public static <T> byte[] serialize(T obj) {
		Class<T> cls = (Class<T>) obj.getClass();
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try {
			Schema<T> schema = getSchema(cls);
			return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		} finally {
			buffer.clear();
		}
	}

	/**
	 * 反序列化
	 *
	 * @param data
	 * @param cls
	 * @param <T>
	 * @return
	 */
	public static <T> T deserialize(byte[] data, Class<T> cls) {
		try {
			Schema<T> schema = getSchema(cls);
			T message = schema.newMessage();
			ProtostuffIOUtil.mergeFrom(data, message, schema);
			return message;
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		MsgInfo info = MsgUtil.buildMsg("11", "sdfsd");
		byte[] bs = serialize(info);
		System.out.println(bs);
		MsgInfo info2 = deserialize(bs, MsgInfo.class);
		System.out.println(info2);
	}
}
