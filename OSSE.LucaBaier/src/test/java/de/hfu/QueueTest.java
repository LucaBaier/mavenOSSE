package de.hfu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QueueTest {
	
	@Test
	public void enqueueTest() {
		Queue queue = new Queue(3);
		
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
	}
	
	@Test
	public void dequeueTest() {
		Queue queue = new Queue(3);
		
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		assertEquals(1, queue.dequeue());
		assertEquals(2, queue.dequeue());
		assertEquals(4, queue.dequeue());
	}
	
	@Test(expected = IllegalStateException.class)
	public void testEmptyDequeue() {
		Queue queue = new Queue(3);
		
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);

		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
	}

}
