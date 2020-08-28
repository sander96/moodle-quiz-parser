# Moodle quiz parser

Some questions require JS to be disabled.

## Environment setup

Run gradle task `setupMoodleLangpacks`

More information in [docker/README.md](docker/README.md)

### Language list script

Get list of languages in https://download.moodle.org/langpack/3.9/.

``` javascript
Array.from(document.querySelectorAll('table > tbody > tr > td > a'))
    .map(node => node.href.split('/').pop().split('.')[0])
    .join('\n');

```
