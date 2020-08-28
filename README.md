# Moodle quiz parser

Some questions require JS to be disabled.

## Dev environment setup

Run gradle task `setupMoodleLangpacks`

More information in [docker/README.md](docker/README.md)

## Including in other projects

### Gradle

1. Add it in your root build.gradle at the end of repositories:
    ``` groovy
    allprojects {
        repositories {
            maven { url 'https://jitpack.io' }
        }
    }
    ```

1. Add the dependency:

    ``` groovy
    dependencies {
        implementation 'com.github.sander96:moodle-quiz-parser:1.0.0'
    }
    ```

## Example code

Example HTML:
``` html
<div id="question-34-1" class="que calculated deferredfeedback incorrect">
   <div class="info">
      <h3 class="no">Question <span class="qno">1</span></h3>
      <div class="state">Incorrect</div>
      <div class="grade">Mark 0.00 out of 1.00</div>
      <div class="questionflag editable" aria-atomic="true" aria-relevant="text" aria-live="assertive" id="yui_3_17_2_1_1594743934621_25"><input type="hidden" name="q34:1_:flagged" value="0"><input type="hidden" value="qaid=42&amp;qubaid=34&amp;qid=4&amp;slot=1&amp;checksum=ea24f17f74b76ca6cfada87511885253&amp;sesskey=6uuHkREqBY&amp;newstate=" class="questionflagpostdata"><input type="hidden" class="questionflagvalue" id="q34:1_:flaggedcheckbox" name="q34:1_:flagged" value="0"><input type="image" class="questionflagimage" src="http://localhost:8090/theme/image.php/boost/core/1592650457/i/unflagged" title="Flag this question for future reference" alt="Not flagged"><span class="questionflagtext" title="Flag this question for future reference">Flag question</span></div>
   </div>
   <div class="content" id="yui_3_17_2_1_1594743934621_48">
      <div class="formulation clearfix" id="yui_3_17_2_1_1594743934621_47">
         <h4 class="accesshide">Question text</h4>
         <input type="hidden" name="q34:1_:sequencecheck" value="3">
         <div class="qtext">Calculate the area of a rectangle. 4.7 is the base and 7.7 is the height.<br></div>
         <div class="ablock form-inline"><label for="q34:1_answer">Answer:</label><span class="answer"><input type="text" name="q34:1_answer" value="33" id="q34:1_answer" size="30" class="form-control d-inline incorrect" readonly="readonly"><i class="icon fa fa-remove text-danger fa-fw " title="Incorrect" aria-label="Incorrect"></i></span></div>
      </div>
      <div class="outcome clearfix">
         <h4 class="accesshide">Feedback</h4>
         <div class="feedback">
            <div class="generalfeedback">
               <p>general feedback ...<br></p>
            </div>
            <div class="rightanswer">The correct answer is: 36.19</div>
         </div>
      </div>
   </div>
</div>

```

Example Java code:
``` java
Quiz quiz = QuizParser.parse(html);
NumericalQuestion numericalQuestion = (NumericalQuestion) quiz.getQuestions().get(0);

System.out.println(numericalQuestion.getQuestionText());    // Calculate the area of a rectangle. 4.7 is the base and 7.7 is the height.

System.out.println(numericalQuestion.getTextField().getText()); // Answer:
System.out.println(numericalQuestion.getTextField().getInput().getValue()); // 33

System.out.println(numericalQuestion.getOutcome().getRightAnswer());    // The correct answer is: 36.19

System.out.println(numericalQuestion.getInfo().getGrade().getMax());    // 1.0
System.out.println(numericalQuestion.getInfo().getGrade().getMark());   // 0.0
```

### Language list script

Get list of languages in https://download.moodle.org/langpack/3.9/.

``` javascript
Array.from(document.querySelectorAll('table > tbody > tr > td > a'))
    .map(node => node.href.split('/').pop().split('.')[0])
    .join('\n');

```
