abstract class LongString extends PrivateInterface {

	public abstract void add(LongString val);

	public abstract int getLength();

	public abstract char charAt(int i);

	public abstract long getHash();

	public abstract LongString substring(int a, int b);

	public abstract String toString();
}
