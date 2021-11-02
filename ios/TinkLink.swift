@objc(TinkLink)
class TinkLink: NSObject {

    @objc(multiply:withB:withResolver:withRejecter:)
    func multiply(a: Float, b: Float, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        resolve(a*b)
    }

    @objc(displayTinkLink:withB:withResolver:withRejecter:)
    func displayTinkLink(a: String, b: String, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        reject("\(a) + \(b)")
    }
    
}
