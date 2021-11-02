import Foundation
import TinkLink
import TinkLinkUI

@objc(TinkLink)
class TinkLink: NSObject {

    @objc(multiply:withB:withResolver:withRejecter:)
    func multiply(a: Float, b: Float, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        resolve(a*b)
    }

    @objc(displayTinkLink:withB:withResolver:withRejecter:)
    func displayTinkLink(a: String, b: String, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        //
    DispatchQueue.main.async {
    
            let redirectURI = NSURL(string: a)

            let configuration = TinkLinkConfiguration(clientID: b, appURI: redirectURI! as URL)
            let scopes: [Scope] = [
                .accounts(.read),
            ]
        
        let tinkLinkViewController = TinkLinkViewController(configuration: configuration, market: "SE", scopes: scopes) { (result) in
        switch (result) {
        case .success(let data):
            resolve("\(data.code.rawValue)")
        case .failure(let data):
            resolve("\(data)")
        }
        }
        
        UIApplication.shared.windows.first?.rootViewController?.present(tinkLinkViewController, animated: true)
        }
        //
    }
    
}
