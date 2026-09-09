package com.goovat.gvtboard.keyboard

class CurrentWordService(
    private val contextService: TextEditingContextService,
    private val detector: CurrentWordDetector = CurrentWordDetector()
) {

    fun readCurrentWord(context: TextEditingContext): String =
        detector.detect(context)

    fun readCurrentWord(): String =
        readCurrentWord(contextService.read())
}
