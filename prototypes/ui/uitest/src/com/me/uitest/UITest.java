package com.me.uitest;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class UITest implements ApplicationListener {
	private Stage stage;


	private static class SelLabel extends ChangeListener {
		private Label label;
		private List list;

		public SelLabel(Skin skin, List list) {
			label = new Label("<...>", skin);
			this.list = list;
			list.addListener(this);
		}
		
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			label.setText(list.getSelection());
		}
		
		public Label getLabel() {
			return label;
		}

	};
	
	public void create() {
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		Gdx.input.setInputProcessor(stage);
		
		final Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
				
		List[] lists = new List[] {
			new List(new String[]{"Flamethrower", "Hammerban", "Fus-Ro-Dah"}, skin),
			new List(new String[]{"Liquid munitions", "Derp noises", "Blinking light"}, skin),
			new List(new String[]{"Louis' pills", "God mode", "Beginner's luck", "Slow-mo",
					"Double-jump"}, skin)};
		
		Table table = new Table(skin), bottom = new Table(skin);
		table.setFillParent(false);
		table.setBackground("white");
		SplitPane container = new SplitPane(table, bottom, true, skin);
		container.setFillParent(true);
		stage.addActor(container);
		//stage.addActor(bottom);

		// Add widgets to the table here		
		bottom.row();
		final Label lblAnswer = new Label("<...>", skin);
		bottom.add(lblAnswer);
		TextButton btn = new TextButton("Pwet", skin);
		btn.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				new Dialog("Some Dialog", skin, "dialog") {
					protected void result (Object object) {
						lblAnswer.setText("Chosen: " + object);
					}
				}.text("Are you enjoying this demo?").button("Yes", true).button("No", false).key(Keys.ENTER, true)
					.key(Keys.ESCAPE, false).show(stage);
			}
		});
		bottom.add(btn);
		bottom.add(new TextField("...", skin));
		bottom.row();
		SelLabel[] labels = new SelLabel[lists.length];

		table.row();
		table.add(new Label("Weapons", skin));
		table.add(new Label("Bonus", skin));
		table.add(new Label("Skills", skin));
		table.row();

		for (int i = 0; i < lists.length; i++) {
			labels[i] = new SelLabel(skin, lists[i]);
			bottom.add(labels[i].getLabel());
			table.add(lists[i]);
		}
		bottom.pack();
		table.pack();
		container.pack();
	}

	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		Table.drawDebug(stage); // This is optional, but enables debug lines for
								// tables.
	}

	public void dispose() {
		stage.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
}
