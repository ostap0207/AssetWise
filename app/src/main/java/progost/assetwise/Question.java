package progost.assetwise;

/**
 * Created by ostap_000 on 20/02/2016.
 */
public class Question{

    private String text;
    private int yes;
    private int no;


    public Question(String text, int yes, int no) {
        this.text = text;
        this.yes = yes;
        this.no = no;
    }

    public int answer(Answer answer) {
        switch (answer) {
            case YES: return yes;
            case NO: return no;
        }
        return 0;
    }

    public String getText() {
        return text;
    }
}
