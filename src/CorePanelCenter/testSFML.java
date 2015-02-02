package CorePanelCenter;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class testSFML extends Thread 
{

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		RenderWindow win = new RenderWindow();
		win.create(new VideoMode(640, 480), "THOCED FrameWork (Test QuadTree)");

		win.setFramerateLimit(60);
		win.setMouseCursorVisible(true);
		
		
	
		while(win.isOpen())
		{
			//Handle events
		    for(Event event : win.pollEvents()) 
		    {
	
		        if(event.type == Event.Type.CLOSED) 
		        {
		          
		            win.close();
		        }
		    }
		}
	}
	
}
