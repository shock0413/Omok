package exam.omok;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Omok_Exam01 extends JFrame {
	private final static int black = 1;				// »Ê
	private final static int white = 2;				// πÈ
	private int index = 1;
	private int board[][] = new int[19][19];
	
	public Omok_Exam01() {
		this.setTitle("∑ª¡÷∑Í ø¿∏Ò");
		this.setSize(600, 630);
		this.setResizable(false);
		this.getContentPane().setBackground(new java.awt.Color(255, 161, 0));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int getX, getY;
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(e.getY() < 60)
						return;
					else if(index == black) {
						getX = (e.getX()/10) * 10 - 30;
						getY = (e.getY()/10) * 10 - 30;
						for(int i = 0; i < 600; i = i + 30) {
							if(getX <= i) {
								for(int j = 0; j < 600; j = j + 30) {
									if(getY <= j) {
										if(check((j+15)/30-1, (i+15)/30) == false) {
											Graphics stone = getGraphics();
											insert((j+15)/30-1, (i+15)/30, black);
											stone.fillOval(i+15, j+15, 30, 30);
											index = white;
											victory((j+15)/30-1, (i+15)/30);						
											break;
										} else
											break;
									}
								}
								break;
							}
						}
					} else if(index == white){
						Graphics stone = getGraphics();
						stone.setColor(Color.WHITE);
						getX = (e.getX()/10) * 10 - 30;
						getY = (e.getY()/10) * 10 - 30;
						for(int i = 0; i < 600; i = i + 30) {
							if(getX <= i) {
								for(int j = 0; j < 600; j = j + 30) {
									if(getY <= j) {
										if(check((j+15)/30-1, (i+15)/30) == false) {
												insert((j+15)/30-1, (i+15)/30, white);
												stone.fillOval(i+15, j+15, 30, 30);
												index = black;
												victory((j+15)/30-1, (i+15)/30);												
												break;
										} else
											break;
									}
								}
								break;
							}
						}
					}
				}
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(java.awt.Color.BLACK);
		for(int i = 0; i < this.getWidth(); i=i+30) {
			g.drawLine(30+i,60, 30+i, 600);
			g.drawLine(30, 60+i, 570, 60+i);
		}
		for(int i = 0; i < 570; i=i+180) {
			for(int j = 0; j < 570; j=j+180) {
				g.fillOval(115+i, 145+j, 10, 10);
			}
		}
	}
	
	public void insert(int r, int c, int color) {
		if(board[r][c] == 0) {
			if(color == black) {
				board[r][c] = black;
			} else if(color == white) {
				board[r][c] = white;
			}
		}
		else
			return;
	}
	
	public boolean check(int r, int c) {
		if(board[r][c] == 0)
			return false;
		else
			return true;
	}
	
	public void count(int y, int x, int count) {
		if(board[y][x]==black) {
			if(count==5)
				JOptionPane.showMessageDialog(null, "»Ê¿« Ω¬");
		} else if(board[y][x]==white) {
			if(count==5)
				JOptionPane.showMessageDialog(null, "πÈ¿« Ω¬");
		}		
	}
	
	public void victory(int _y, int _x) {
		int x, y;
		int count;
		
		// »Ê¿« Ω¬ ø©∫Œ
		// ∞°∑Œ
		x=_x;
		y=_y;
		count=0;
		if(x>0 && x<18) {
			while(board[_y][x-1]==black) {
				x--;
				if(x<1)
					break;
			}
			while(board[_y][x++]==black) {
				count++;
				if(x>18)
					break;
			}
			count(_y, _x, count);
		} else if(x==0) {
			count++;
			while(board[_y][x+1]==black) {
				x++;
				count++;
				if(x>18)
					break;
			}
			count(_y, _x, count);
		} else if(x==18) {
			count++;
			while(board[_y][x-1]==black) {
				x--;
				count++;
				if(x<1)
					break;
			}
			count(_y, _x, count);
		}
		// ºº∑Œ
		x=_x;
		y=_y;
		count=0;
		if(y>0 && y<18) {
			while(board[y-1][_x]==black) {
				y--;
				if(y<1)
					break;
			}
			while(board[y++][_x]==black) {
				count++;
				if(y>18)
					break;
			}
			count(_y, _x, count);
		} else if(y==0) {
			count++;
			while(board[y+1][_x]==black) {
				y++;
				count++;
				if(y>18)
					break;
			}
			count(_y, _x, count);
		} else if(y==18) {
			count++;
			while(board[y-1][_x]==black) {
				y--;
				count++;
				if(y<1)
					break;
			}
			count(_y, _x, count);
		}
		// ¥Î∞¢º±¢Ÿ
		x=_x;
		y=_y;
		count=0;
		if(x>0 && x<18 && y>0 && y<18) {
			while(board[y-1][x-1]==black) {
				x--;
				y--;
				if(x<1 && y<1)
					break;
				else if(x<1 || y<1)
					break;
			}
			while(board[y++][x++]==black) {
				count++;
				if(x>18 && y>18)
					break;
				else if(x>18 || y>18)
					break;
			}
			count(_y, _x, count);
			// ¥Î∞¢º±¢◊
			x=_x;
			y=_y;
			count=0;
			while(board[y-1][x+1]==black) {
				x++;
				y--;
				if(x>18 && y<1)
					break;
				else if(x>18 || y<1)
					break;
			}
			while(board[y++][x--]==black) {
				count++;
				if(x<1 && y>18)
					break;
				else if(x<1 || y>18)
					break;
			}
			count(_y, _x, count);
		} else if(x==0 && y==0) {
			x=_x;
			y=_y;
			count=1;
			while(board[y+1][x+1]==black) {
				x++;
				y++;
				count++;
				if(x>18 && y>18)
					break;
			}
			count(_y, _x, count);
		} else if(x==0 && y==18) {
			x=_x;
			y=_y;
			count=1;
			while(board[y-1][x+1]==black) {
				x++;
				y--;
				count++;
				if(x>18 && y<1)
					break;
			}
			count(_y, _x, count);
		} else if(x==18 && y==0) {
			x=_x;
			y=_y;
			count=1;
			while(board[y+1][x-1]==black) {
				x--;
				y++;
				count++;
				if(x<1 && y>18)
					break;
			}
			count(_y, _x, count);
		} else if(x==18 && y==18) {
			x=_x;
			y=_y;
			count=1;
			while(board[y-1][x-1]==black) {
				x--;
				y--;
				count++;
				if(x<1 && y<1)
					break;
			}
			count(_y, _x, count);
		} else if(x==0 && y>0 && y<18) {
			x=_x;
			y=_y;
			count=1;
			while(board[y+1][x+1]==black) {
				x++;
				y++;
				count++;
				if(x>18 && y>18)
					break;
				else if(x>18 || y>18)
					break;
			}
			count(_y, _x, count);
			x=_x;
			y=_y;
			count=1;
			while(board[y-1][x+1]==black) {
				y--;
				x++;
				count++;
				if(x>18 && y<1)
					break;
				else if(x>18 && y<1)
					break;
			}
			count(_y, _x, count);
		} else if(x==18 && y>0 && y<18) {
			x=_x;
			y=_y;
			count=1;
			while(board[y-1][x-1]==black) {
				x--;
				y--;
				count++;
				if(x<1 && y<1)
					break;
				else if(x<1 || y<1)
					break;
			}
			count(_y, _x, count);
			x=_x;
			y=_y;
			count=1;
			while(board[y+1][x-1]==black) {
				x--;
				y++;
				count++;
				if(x<1 && y>18)
					break;
				else if(x<1 || y>18)
					break;
			}
			count(_y, _x, count);
		} else if(x>0 && x<18 && y==0) {
			x=_x;
			y=_y;
			count=1;
			while(board[y+1][x+1]==black) {
				x++;
				y++;
				count++;
				if(x>18 && y>18)
					break;
				else if(x>18 || y>18)
					break;
			}
			count(_y, _x, count);
			x=_x;
			y=_y;
			count=1;
			while(board[y+1][x-1]==black) {
				x--;
				y++;
				count++;
				if(x<1 && y>18)
					break;
				else if(x<1 || y>18)
					break;
			}
			count(_y, _x, count);
		} else if(x>0 && x<18 && y==18) {
			x=_x;
			y=_y;
			count=1;
			while(board[y-1][x-1]==black) {
				x--;
				y--;
				count++;
				if(x<1 && y<1)
					break;
				else if(x<1 || y<1)
					break;
			}
			count(_y, _x, count);
			x=_x;
			y=_y;
			count=1;
			while(board[y-1][x+1]==black) {
				x++;
				y--;
				count++;
				if(x>18 && y<0)
					break;
				else if(x>18 || y<1)
					break;
			}
			count(_y, _x, count);
		}
		
		// πÈ¿« Ω¬ ø©∫Œ
				// ∞°∑Œ
				x=_x;
				y=_y;
				count=0;
				if(x>0 && x<18) {
					while(board[_y][x-1]==white) {
						x--;
						if(x<1)
							break;
					}
					while(board[_y][x++]==white) {
						count++;
						if(x>18)
							break;
					}
					count(_y, _x, count);
				} else if(x==0) {
					count++;
					while(board[_y][x+1]==white) {
						x++;
						count++;
						if(x>18)
							break;
					}
					count(_y, _x, count);
				} else if(x==18) {
					count++;
					while(board[_y][x-1]==white) {
						x--;
						count++;
						if(x<1)
							break;
					}
					count(_y, _x, count);
				}
				// ºº∑Œ
				x=_x;
				y=_y;
				count=0;
				if(y>0 && y<18) {
					while(board[y-1][_x]==white) {
						y--;
						if(y<1)
							break;
					}
					while(board[y++][_x]==white) {
						count++;
						if(y>18)
							break;
					}
					count(_y, _x, count);
				} else if(y==0) {
					count++;
					while(board[y+1][_x]==white) {
						y++;
						count++;
						if(y>18)
							break;
					}
					count(_y, _x, count);
				} else if(y==18) {
					count++;
					while(board[y-1][_x]==white) {
						y--;
						count++;
						if(y<1)
							break;
					}
					count(_y, _x, count);
				}
				// ¥Î∞¢º±¢Ÿ
				x=_x;
				y=_y;
				count=0;
				if(x>0 && x<18 && y>0 && y<18) {
					while(board[y-1][x-1]==white) {
						x--;
						y--;
						if(x<1 && y<1)
							break;
						else if(x<1 || y<1)
							break;
					}
					while(board[y++][x++]==white) {
						count++;
						if(x>18 && y>18)
							break;
						else if(x>18 || y>18)
							break;
					}
					count(_y, _x, count);
					// ¥Î∞¢º±¢◊
					x=_x;
					y=_y;
					count=0;
					while(board[y-1][x+1]==white) {
						x++;
						y--;
						if(x>18 && y<1)
							break;
						else if(x>18 || y<1)
							break;
					}
					while(board[y++][x--]==white) {
						count++;
						if(x<1 && y>18)
							break;
						else if(x<1 || y>18)
							break;
					}
					count(_y, _x, count);
				} else if(x==0 && y==0) {
					x=_x;
					y=_y;
					count=1;
					while(board[y+1][x+1]==white) {
						x++;
						y++;
						count++;
						if(x>18 && y>18)
							break;
					}
					count(_y, _x, count);
				} else if(x==0 && y==18) {
					x=_x;
					y=_y;
					count=1;
					while(board[y-1][x+1]==white) {
						x++;
						y--;
						count++;
						if(x>18 && y<1)
							break;
					}
					count(_y, _x, count);
				} else if(x==18 && y==0) {
					x=_x;
					y=_y;
					count=1;
					while(board[y+1][x-1]==white) {
						x--;
						y++;
						count++;
						if(x<1 && y>18)
							break;
					}
					count(_y, _x, count);
				} else if(x==18 && y==18) {
					x=_x;
					y=_y;
					count=1;
					while(board[y-1][x-1]==white) {
						x--;
						y--;
						count++;
						if(x<1 && y<1)
							break;
					}
					count(_y, _x, count);
				} else if(x==0 && y>0 && y<18) {
					x=_x;
					y=_y;
					count=1;
					while(board[y+1][x+1]==white) {
						x++;
						y++;
						count++;
						if(x>18 && y>18)
							break;
						else if(x>18 || y>18)
							break;
					}
					count(_y, _x, count);
					x=_x;
					y=_y;
					count=1;
					while(board[y-1][x+1]==white) {
						y--;
						x++;
						count++;
						if(x>18 && y<1)
							break;
						else if(x>18 && y<1)
							break;
					}
					count(_y, _x, count);
				} else if(x==18 && y>0 && y<18) {
					x=_x;
					y=_y;
					count=1;
					while(board[y-1][x-1]==white) {
						x--;
						y--;
						count++;
						if(x<1 && y<1)
							break;
						else if(x<1 || y<1)
							break;
					}
					count(_y, _x, count);
					x=_x;
					y=_y;
					count=1;
					while(board[y+1][x-1]==white) {
						x--;
						y++;
						count++;
						if(x<1 && y>18)
							break;
						else if(x<1 || y>18)
							break;
					}
					count(_y, _x, count);
				} else if(x>0 && x<18 && y==0) {
					x=_x;
					y=_y;
					count=1;
					while(board[y+1][x+1]==white) {
						x++;
						y++;
						count++;
						if(x>18 && y>18)
							break;
						else if(x>18 || y>18)
							break;
					}
					count(_y, _x, count);
					x=_x;
					y=_y;
					count=1;
					while(board[y+1][x-1]==white) {
						x--;
						y++;
						count++;
						if(x<1 && y>18)
							break;
						else if(x<1 || y>18)
							break;
					}
					count(_y, _x, count);
				} else if(x>0 && x<18 && y==18) {
					x=_x;
					y=_y;
					count=1;
					while(board[y-1][x-1]==white) {
						x--;
						y--;
						count++;
						if(x<1 && y<1)
							break;
						else if(x<1 || y<1)
							break;
					}
					count(_y, _x, count);
					x=_x;
					y=_y;
					count=1;
					while(board[y-1][x+1]==white) {
						x++;
						y--;
						count++;
						if(x>18 && y<0)
							break;
						else if(x>18 || y<1)
							break;
					}
					count(_y, _x, count);
				}
	}
	
	public void handicap(int y, int x) {		// Ω÷ªÔ
		if(board[y][x]==black) {
			while((board[y][x-1]==black && board[y][x+1]==black) &&
					(board[y-1][x]==black && board[y+1][x]==black)) {
				JOptionPane.showMessageDialog(null, "Ω÷ªÔπﬂª˝!");
			}
		}
	}
	
	public static void main(String[] args) {
		Omok_Exam01 f = new Omok_Exam01();
	}
}