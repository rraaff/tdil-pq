package com.tdil.lojack.camera.applet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tdil.lojack.camera.IPCamera;
import com.tdil.utils.encryption.DesEncrypter;

/**
 * Class <code>AppletCamara</code> is responsible of establishing a directo connection
 * to the camera and refresh its image at a regular interval.
 */
public class AppletCamara extends javax.swing.JApplet {

	private static final long serialVersionUID = -2966192681788304871L;

	private IPCamera camera = null;

	private PanelCamara panelCamara;

	private JButton jButtonDerecha;

	private JButton jButtonIzquierda;

	private JPanel jPanelHorizontal;

	private JButton jButtonAbajo;

	private JButton jButtonArriba;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	private static Image noise = null;

	private long refreshInterval;
	private JLabel lblNewLabel;

	public AppletCamara() {
		initGUI();
	}

	/**
	 * Initializes the GUI. Auto-generated code - any changes you make will
	 * disappear.
	 */
	public void initGUI() {
		try {
			preInitGUI();
			jPanelHorizontal = new JPanel();
			jPanelHorizontal.setForeground(Color.WHITE);
			jButtonIzquierda = new JButton();
			configure(jButtonIzquierda);
			
			jButtonDerecha = new JButton();
			configure(jButtonDerecha);
			
			this.setSize(new java.awt.Dimension(350, 241));
			this.getContentPane().setBackground(
					Color.BLACK);
			getContentPane().setLayout(new BorderLayout(0, 0));
			jPanelHorizontal.setBackground(Color.WHITE);
			this.getContentPane().add(
					jPanelHorizontal, BorderLayout.SOUTH);
			jPanelHorizontal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
			
			lblNewLabel = new JLabel("00:00:00");
			jPanelHorizontal.add(lblNewLabel);

			jButtonIzquierda.setIcon(new ImageIcon(getBytesFrom(AppletCamara.class
					.getResourceAsStream("applet_left_off.png"))));
			jButtonIzquierda.setPressedIcon(new ImageIcon(getBytesFrom(AppletCamara.class
					.getResourceAsStream("applet_left_on.png"))));
			jButtonIzquierda.setRolloverIcon(new ImageIcon(getBytesFrom(AppletCamara.class
					.getResourceAsStream("applet_left_over.png"))));
			jButtonIzquierda
					.setToolTipText("Mover la cámara hacia la izquierda");
			jButtonIzquierda.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
			jPanelHorizontal.add(jButtonIzquierda);
			jButtonIzquierda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonIzquierdaActionPerformed(evt);
				}
			});
			jButtonArriba = new JButton();
			configure(jButtonArriba);
			jPanelHorizontal.add(jButtonArriba);

						jButtonArriba.setIcon(new ImageIcon(getBytesFrom(AppletCamara.class
								.getResourceAsStream("applet_up_off.png"))));
						jButtonArriba.setPressedIcon(new ImageIcon(getBytesFrom(AppletCamara.class
								.getResourceAsStream("applet_up_on.png"))));
						jButtonArriba.setRolloverIcon(new ImageIcon(getBytesFrom(AppletCamara.class
								.getResourceAsStream("applet_up_over.png"))));
						jButtonArriba.setToolTipText("Mover la cámara hacia arriba");
						jButtonArriba.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
						jButtonArriba.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jButtonArribaActionPerformed(evt);
							}
						});
			jButtonAbajo = new JButton();
			configure(jButtonAbajo);
			jPanelHorizontal.add(jButtonAbajo);

						jButtonAbajo.setIcon(new ImageIcon(getBytesFrom(AppletCamara.class
								.getResourceAsStream("applet_down_off.png"))));
						jButtonAbajo.setPressedIcon(new ImageIcon(getBytesFrom(AppletCamara.class
								.getResourceAsStream("applet_down_on.png"))));
						jButtonAbajo.setRolloverIcon(new ImageIcon(getBytesFrom(AppletCamara.class
								.getResourceAsStream("applet_down_over.png"))));
						jButtonAbajo.setToolTipText("Mover la cámara hacia abajo");
						jButtonAbajo.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
						jButtonAbajo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jButtonAbajoActionPerformed(evt);
							}
						});

			jButtonDerecha.setIcon(new ImageIcon(getBytesFrom(AppletCamara.class
					.getResourceAsStream("applet_right_off.png"))));
			jButtonDerecha.setPressedIcon(new ImageIcon(getBytesFrom(AppletCamara.class
					.getResourceAsStream("applet_right_on.png"))));
			jButtonDerecha.setRolloverIcon(new ImageIcon(getBytesFrom(AppletCamara.class
					.getResourceAsStream("applet_right_over.png"))));
			jButtonDerecha.setToolTipText("Mover la cámara hacia la derecha");
			jButtonDerecha.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
			jPanelHorizontal.add(jButtonDerecha);
			jButtonDerecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonDerechaActionPerformed(evt);
				}
			});

			postInitGUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void configure(JButton jButtonIzquierda) {
		jButtonIzquierda.setOpaque(false);
		jButtonIzquierda.setContentAreaFilled(false);
		jButtonIzquierda.setBorderPainted(false);
	}

	public byte[] getBytesFrom(InputStream in) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024]; // you can configure the buffer size
			int length;
			while ((length = in.read(buffer)) != -1) out.write(buffer, 0, length); //copy streams
			 // call this in a finally block
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** Add your pre-init code in here */
	public void preInitGUI() {
	}

	/** Add your post-init code in here */
	public void postInitGUI() {
		panelCamara = new PanelCamara();
		panelCamara.setForeground(Color.WHITE);
		panelCamara.setBackground(new java.awt.Color(255, 255, 255));
		this.getContentPane().add(
				panelCamara);
	}

	public void init() {
		super.init();
		String username = getParameter("username");
		String url = null;
		String password = null;
		String refreshIntervalParam = null;
		String model = null;
		int connectTimeOut = 5000;
		int readTimeOut = 5000;
		if (username == null || username.length() == 0) {
			URL urlParams;
			try {
				urlParams = new URL(this.getCodeBase().toString() + "/cameraConfig");
				byte[] params = getBytesFrom(urlParams.openStream());
				DesEncrypter encrypter = new DesEncrypter("esta es la clave de la camara para lojack");
				byte[] conf = encrypter.decrypt(params);
				String config = new String(conf);
				String parameters[] = config.split(",");
				username = parameters[0];
				password = parameters[1];
				url = parameters[2];
				model = parameters[3];
				connectTimeOut = Integer.parseInt(parameters[4]);
				readTimeOut = Integer.parseInt(parameters[5]);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			url = getParameter("url");
			password = getParameter("password");
			refreshInterval = 1000;
			refreshIntervalParam = getParameter("refresh");
			if (refreshIntervalParam != null) {
				refreshInterval = Long.parseLong(refreshIntervalParam);
			}

			model = getParameter("model");
		}
		camera = IPCamera.createIPCamera(model, url, username, password);
		camera.setConnectTimeOut(connectTimeOut);
		camera.setReadTimeOut(readTimeOut);

		Thread thread = new Thread() {
			/*
			 * (non-Javadoc)
			 *
			 * @see java.lang.Thread#run()
			 */
			public void run() {
				super.run();
				while (true) {
					ByteArrayInputStream in = null;
					boolean hasImage = false;
					try {
						in = camera.nextFrame();
						if (in != null) {
							hasImage = true;
							Image imagen = ImageIO.read(in);
							panelCamara.setImage(imagen);
							panelCamara.repaint();
							showTimeOk();
							sleep(refreshInterval);
						} else {
							showTimeErr();
							System.out.println("null image");
							if (!hasImage) {
								if (getNoise() != null) {
									panelCamara.setImage(getNoise());
									panelCamara.repaint();
								}
							}
						}
					} catch (IOException e) {
						showTimeErr();
						e.printStackTrace();
						if (!hasImage) {
							if (getNoise() != null) {
								panelCamara.setImage(getNoise());
								panelCamara.repaint();
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						try {
							if (in != null) {
								in.close();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			}
		};
		thread.start();
	}

	protected void showTimeOk() {
		lblNewLabel.setText(dateFormat.format(new Date()));
		lblNewLabel.setForeground(Color.BLACK);
	}
	protected void showTimeErr() {
		lblNewLabel.setText(dateFormat.format(new Date()));
		lblNewLabel.setForeground(Color.RED);
	}

	/** Auto-generated event handler method */
	protected void jButtonHomeActionPerformed(ActionEvent evt) {
	}

	/** Auto-generated event handler method */
	protected void jButtonDerechaActionPerformed(ActionEvent evt) {
		new Thread() {
			@Override
			public void run() {
				camera.right();
			}
		}.start();
	}

	/** Auto-generated event handler method */
	protected void jButtonIzquierdaActionPerformed(ActionEvent evt) {
		new Thread() {
			@Override
			public void run() {
				camera.left();
			}
		}.start();
	}

	/** Auto-generated event handler method */
	protected void jButtonArribaActionPerformed(ActionEvent evt) {
		new Thread() {
			@Override
			public void run() {
				camera.up();
			}
		}.start();
	}

	/** Auto-generated event handler method */
	protected void jButtonAbajoActionPerformed(ActionEvent evt) {
		new Thread() {
			@Override
			public void run() {
				camera.down();
			}
		}.start();
	}

	public static Image getNoise() {
		if (noise == null) {
			InputStream in = null;
			try {
				in = AppletCamara.class.getResourceAsStream("noise.jpg");
				noise = ImageIO.read(in);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		return noise;
	}

}
