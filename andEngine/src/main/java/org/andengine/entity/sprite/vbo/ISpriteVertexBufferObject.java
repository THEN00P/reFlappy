package org.andengine.entity.sprite.vbo;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.IVertexBufferObject;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:40:47 - 28.03.2012
 */
public interface ISpriteVertexBufferObject extends IVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void onUpdateColor(final Sprite pSprite);
	/* renamed from: a */
	void onUpdateVertices(Sprite sprite, float pX, float pY, float pX1, float pY1, float pX2, float pY2, float width, float height);
	public void onUpdateVertices(final Sprite pSprite);
	public void onUpdateTextureCoordinates(final Sprite pSprite);
}