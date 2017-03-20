package xyz.nobu.beatbox;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by nobu on 3/18/17.
 */
public class SoundViewModelTest {
    /**
     * Sound is a simple data object with no behavior to break, so
     * it is safe not to mock it.
     * SoundViewModel is named as mSubject instead of mSoundViewModel.
     * This is a convetion we like to use in our tests for two reasons:
     *  - It makes it clear that mSubject is the object under test (and
     *  the other objects are not).
     *  - If any methods on SoundViewModel are ever moved to a different
     *  class, the test methods can be cut and pasted over without
     *  renaming mSubject.
     */
    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject;

    @Before
    public void setUp() throws Exception {
        mBeatBox = mock(BeatBox.class);
        mSound = new Sound("assetPath");
        mSubject = new SoundViewModel(mBeatBox);
        mSubject.setSound(mSound);
    }

    @Test
    public void exposesSoundNameAsTitle() {
        assertThat(mSubject.getTitle(), is(mSound.getName()));
    }

    @Test
    public void callsBeatBoxPlayOnButtonClicked() {
        mSubject.onButtonClicked();
        verify(mBeatBox).play(mSound);
    }
}