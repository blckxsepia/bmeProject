package com.bmeproject.game.bmeProject.theatricalScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bmeproject.game.bmeProject.Entity;
import com.bmeproject.game.bmeProject.entity.Type;

/**
 * Basis aller auf einem Screen darstellbaren Karten. Stellt alle Attribute und Funktionen bereit, die zur formellen
 * Darstellung (Grafik, Akustik) nötig sind. Werden durch
 * {@link com.bmeproject.game.bmeProject.battleScreen.player.BattleCard} und
 * {@link com.bmeproject.game.bmeProject.deckScreen.DeckCard} für die jeweiligen Screens erweitert.
 */
public class Card extends Actor
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private static final Texture       TEXTURES     = new Texture("core/assets/visuals/texture_atlas.png");
	private static final TextureRegion TEXTURE_BACK = new TextureRegion(TEXTURES, 0, 0, 204, 300);

	protected final Entity        ENTITY;
	private final   TextureRegion TEXTURE_FRONT;
	protected       Sprite        sprite;

	// ===================================
	// CONSTRUCTOR
	// ===================================

	public Card(Entity entity)
	{
		ENTITY = entity;
		TEXTURE_FRONT = new TextureRegion(TEXTURES, Type.BASE.X, Type.BASE.Y, 204, 300);
		sprite = new Sprite(TEXTURE_FRONT);
	}

	// ===================================
	// METHODS
	// ===================================

	/**
	 * Wird jedes Mal aufgerufen, wenn die Position (x- / y-Koordinaten) einer Instanz dieser Klasse geändert wird,
	 * indem die dafür vorgesehene, gleichnamige Methode der Superklasse überschrieben wird. Gleicht dann die Position
	 * des dazugehörigen Sprites an die der Instanz dieser Klasse an.
	 */
	@Override protected void positionChanged()
	{
		sprite.setPosition(getX(), getY());
	}

	/**
	 * Wird jedes Mal aufgerufen, wenn die Rotation einer Instanz dieser Klasse geändert wird, indem die dafür
	 * vorgesehene, gleichnamige Methode der Superklasse überschrieben wird. Gleicht dann die Rotation des
	 * dazugehörigen Sprites an die der Instanz dieser Klasse an.
	 */
	@Override protected void rotationChanged()
	{
		sprite.setRotation(getRotation());
	}

	/**
	 * Wird jedes Mal aufgerufen, wenn eine Instanz dieser Klasse von der Stage gezeichnet wird, indem die dafür
	 * vorgesehene, gleichnamige Methode der Superklasse überschrieben wird. Zeichnet dann auch
	 * den Sprite der Instanz dieser Klasse.
	 *
	 * @param batch
	 * @param parentAlpha
	 */
	@Override public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		sprite.draw(batch);
	}
}