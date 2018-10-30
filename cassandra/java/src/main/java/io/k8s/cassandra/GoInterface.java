package io.k8s.cassandra;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public interface GoInterface extends Library {
	public String GetEndpoints(String namespace, String service, String seeds);

	public class GoSlice extends Structure {
		public static class ByValue extends GoSlice implements Structure.ByValue {
		}

		public Pointer data;
		public long len;
		public long cap;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "data", "len", "cap" });
		}
	}

	public class GoString extends Structure {
		public static class ByValue extends GoString implements Structure.ByValue {
		}

		public String p;
		public long n;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "p", "n" });
		}
	}
}