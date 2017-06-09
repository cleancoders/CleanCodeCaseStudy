package cleancoderscom.adapters.mvc;

import cleancoderscom.usecases.core.UseCase;

public abstract class MvcUseController<
    RequestModel,
    ResponseModel,
    ViewModel,
    Output,
    INPUT_BOUNDARY extends UseCase<RequestModel, ResponseModel>,
    OUTPUT_BOUNDARY extends OutputBoundary<ViewModel, ResponseModel>,
    VIEW extends View<ViewModel, Output>> {

    private final INPUT_BOUNDARY useCase;
    private final VIEW view;
    private final OUTPUT_BOUNDARY outputBoundary;

    public MvcUseController(INPUT_BOUNDARY useCase, OUTPUT_BOUNDARY outputBoundary, VIEW view) {
        this.useCase = useCase;
        this.view = view;
        this.outputBoundary = outputBoundary;
    }


    public Output handle(Request request) {
        RequestModel requestModel = requestToRequestModel(request);
        ResponseModel responseModel = useCase.execute(requestModel);
        outputBoundary.present(responseModel);
        return view.generateView(outputBoundary.getViewModel());
    }

    public abstract RequestModel requestToRequestModel(Request request);
}

