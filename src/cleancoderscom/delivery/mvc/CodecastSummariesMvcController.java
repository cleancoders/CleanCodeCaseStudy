package cleancoderscom.delivery.mvc;

import cleancoderscom.adapters.mvc.MvcUseController;
import cleancoderscom.adapters.mvc.OutputBoundary;
import cleancoderscom.adapters.mvc.Request;
import cleancoderscom.adapters.mvc.View;
import cleancoderscom.usecases.gateways.Context;
import cleancoderscom.entities.User;
import cleancoderscom.usecases.core.UseCase;
import cleancoderscom.usecases.entities.CodecastSummariesResponse;

public class CodecastSummariesMvcController extends MvcUseController<
        User,
        CodecastSummariesResponse,
        CodecastSummariesViewModel,
        String,
        UseCase<User, CodecastSummariesResponse>,
        OutputBoundary<CodecastSummariesViewModel, CodecastSummariesResponse>,
        View<CodecastSummariesViewModel, String>> {
    public CodecastSummariesMvcController(UseCase<User, CodecastSummariesResponse> usecase, OutputBoundary<CodecastSummariesViewModel, CodecastSummariesResponse> outputBoundary, View<CodecastSummariesViewModel, String> view) {
        super(usecase, outputBoundary, view);
    }

    @Override
    public User requestToRequestModel(Request request) {
        return Context.gateKeeper.getLoggedInUser();
    }
}
