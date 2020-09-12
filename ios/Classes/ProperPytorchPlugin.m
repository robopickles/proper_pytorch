#import "ProperPytorchPlugin.h"
#if __has_include(<proper_pytorch/proper_pytorch-Swift.h>)
#import <proper_pytorch/proper_pytorch-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "proper_pytorch-Swift.h"
#endif

@implementation ProperPytorchPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftProperPytorchPlugin registerWithRegistrar:registrar];
}
@end
